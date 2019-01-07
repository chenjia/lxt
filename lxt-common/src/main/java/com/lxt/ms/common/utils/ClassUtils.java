package com.lxt.ms.common.utils;

import java.net.URL;

public class ClassUtils {

    public static String getPath(String className){
        if(className==null){
            className = "";
        }
        String classTemp = className;
        String classPath = "";
        if(!"".equals(className)){
            if (!className.startsWith("/")) {
                className = "/" + className;
            }
            className = className.replace('.', '/');
            className = className + ".class";

            URL classUrl = new ClassUtils().getClass().getResource(className);

            if (classUrl != null) {
                classPath = "\nClass '" + className + "' found in \n"
                        + classUrl.getFile();
            } else {
                classPath += "class not found in:\n";
                String[] classPathArray = System.getProperty("java.class.path").split(":");
                for(int i=0;i<classPathArray.length;i++){
                    String[] str = classPathArray[i].split(";");
                    for(int j=0;j<str.length;j++){
                        classPath += str[j]+"\n";
                    }
                }
            }
        }
        System.out.println("className:"+className);
        System.out.println("classPath:"+classPath);
        return classPath;
    }
}
