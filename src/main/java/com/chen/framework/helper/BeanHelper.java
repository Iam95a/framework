package com.chen.framework.helper;

import com.chen.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP =
            new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> aClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(aClass);
            BEAN_MAP.put(aClass, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean from beanmap");
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void serBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
