package com.lxt.ms.chat.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.SpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
            pkg.getHead().setMsg("服务调用异常");
        }

        System.out.println("\n[response service]" + service + "/" + method);
        System.out.println(JSONUtils.obj2Json(pkg) + "\n");
        return pkg;
    }

    @SuppressWarnings("unchecked")
    private Packages callService(String service, String method, Packages pkg) {
        Packages result = pkg;

        try {
            ApplicationContext ac = SpringUtils.getApplicationContext();
            Object serviceObj = ac.getBean(service + "Service");
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

                System.out.println("args[i]:"+args[i]);

                if(args[i] instanceof Map){
                    Object instance = paramClass[i].newInstance();

                    System.out.println("instance:"+instance);

                    if(!(instance instanceof Map)){
                        BeanUtils.copyProperties(instance, args[i]);
                        args[i] = instance;
                    }
                    System.out.println("args:"+args[i]);
                }else if("$userId".equalsIgnoreCase(paramNames[i])){
                    args[i] = pkg.getHead().getUserId();
                }else if(paramClass[i].isArray()){
                    List list = (List) args[i];
                    Object[] obj = (Object[]) Array.newInstance(paramClass[i].getComponentType(), list.size());
                    args[i] = list.toArray(obj);
                }
            }
            result = (Packages) methodObj.invoke(serviceObj, args);
        } catch (Exception e) {
            e.printStackTrace();
            result.getHead().setStatus(500);
            int msgLength = e.getMessage().length() > 100 ? 100 : e
                    .getMessage().length();
            result.getHead().setMsg(
                    "接口调用异常:" + e.getMessage().substring(0, msgLength));
        }

        return result;
    }
}
