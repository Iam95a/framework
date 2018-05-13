package com.chen.framework.helper;

import com.chen.framework.annotation.Inject;
import com.chen.framework.util.ReflectionUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {
    static {
        Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
        if(beanMap!=null&&CollectionUtils.isNotEmpty(beanMap.keySet())){
            for (Map.Entry<Class<?>, Object> classObjectEntry : beanMap.entrySet()) {
                Class<?> beanClass=classObjectEntry.getKey();
                Object beanInstance=classObjectEntry.getValue();
                Field[] fields=beanClass.getDeclaredFields();
                for (Field field : fields) {
                    if(field.isAnnotationPresent(Inject.class)){
                        Class<?> beanFieldClass=field.getType();
                        Object beanFieldInstance=beanMap.get(beanFieldClass);
                        if(beanFieldInstance!=null){
                            ReflectionUtil.setField(beanInstance,
                                    field,beanFieldInstance);
                        }
                    }
                }
            }
        }
    }
}
