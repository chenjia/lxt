package com.lxt.ms.designpattern.proxy.dynamicproxy.gpproxy;

import com.lxt.ms.designpattern.proxy.Person;
import com.lxt.ms.designpattern.proxy.dynamicproxy.jdkproxy.Girl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by Tom on 2019/3/10.
 */
public class GPProxyTest {

    public static void main(String[] args) {
        try {

            //JDK动态代理的实现原理
            Person obj = (Person) new GPMeipo().getInstance(new Girl());
            System.out.println(obj.getClass());
            obj.findLove();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
