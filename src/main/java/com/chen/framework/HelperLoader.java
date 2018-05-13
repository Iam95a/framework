package com.chen.framework;

import com.chen.framework.helper.BeanHelper;
import com.chen.framework.helper.ClassHelper;
import com.chen.framework.helper.ControllerHelper;
import com.chen.framework.helper.IocHelper;
import com.chen.framework.util.ClassUtil;

public class HelperLoader {

    public static  void init(){
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> aClass : classList) {
            ClassUtil.loadClass(aClass.getName(),false);
        }
    }
}
