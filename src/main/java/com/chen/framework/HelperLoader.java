package com.chen.framework;

import com.chen.framework.helper.*;
import com.chen.framework.util.ClassUtil;

public class HelperLoader {

    public static  void init(){
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> aClass : classList) {
            ClassUtil.loadClass(aClass.getName(),true);
        }
    }
}
