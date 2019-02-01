package com.lxt.ms.workflow.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.SpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Controller
public class WorkflowController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        applicationContext = ac;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @ResponseBody
    @RequestMapping(value = "/{service}/{method}", method = RequestMethod.POST)
    public Packages api(HttpServletRequest request, HttpServletResponse httpServletResponse, @PathVariable String service, @PathVariable String method) throws Exception {
        System.out.println("【service api】" + service + "/" + method);

        String requestJson = request.getParameter("request");
        System.out.println(requestJson + "\n");
        Packages pkg = JSONUtils.json2Obj(requestJson, Packages.class);

        try {
            if (pkg.getHead().getStatus() == 200) {
                pkg = callService(service, method, pkg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pkg.getHead().setStatus(500);
            String msg = e.getCause()==null?e.getMessage():e.getCause().getMessage();
            pkg.getHead().setMsg((e.getCause()==null && msg==null)?"服务调用异常":msg.substring(0,msg.length()>100?100:msg.length()));
        }

        System.out.println("\n[response service]" + service + "/" + method);
        System.out.println(JSONUtils.obj2Json(pkg) + "\n");
        return pkg;
    }

    @SuppressWarnings("unchecked")
    private Packages callService(String service, String method, Packages pkg) throws Exception {
        Packages result = pkg;

        Object serviceObj = applicationContext.getBean(service + "Service");
        Class clazz =  serviceObj.getClass();
        Method[] methods = clazz.getMethods();
        Method methodObj = null;
        for (Method m : methods) {
            if (m.getName().equals(method)) {
                methodObj = m;
                break;
            }
        }
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(methodObj);
        Class[] paramClass = methodObj.getParameterTypes();
        Object[] args = new Object[paramNames.length];
        Map<String, Object> map = (Map<String, Object>) pkg.getBody().getData();

        for (int i = 0; i < paramNames.length; i++) {
            args[i] = map.get(paramNames[i]);
            if(args[i] instanceof Map){
                Object instance = paramClass[i].newInstance();
                BeanUtils.copyProperties(instance, args[i]);
                args[i] = instance;
            }else if("$userId".equalsIgnoreCase(paramNames[i])){
                args[i] = pkg.getHead().getUserId();
            }
        }
        result = (Packages) methodObj.invoke(serviceObj, args);

        return result;
    }
}