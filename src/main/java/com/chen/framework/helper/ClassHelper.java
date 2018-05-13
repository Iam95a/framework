package com.chen.framework.helper;

import com.chen.framework.annotation.Controller;
import com.chen.framework.annotation.Service;
import com.chen.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET= ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }


    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<>();

        for (Class<?> aClass : CLASS_SET) {
            if(aClass.isAnnotationPresent(Service.class)){
                classSet.add(aClass);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> aClass : CLASS_SET) {
            if(aClass.isAnnotationPresent(Controller.class)){
                classSet.add(aClass);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet=new HashSet<>();

        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());
        return beanClassSet;
    }
}
