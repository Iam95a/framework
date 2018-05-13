package com.chen.framework.helper;

import com.chen.framework.annotation.RequestMapping;
import com.chen.framework.bean.Handler;
import com.chen.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {
    private static final Map<Request,Handler> REQUEST_HANDLER_MAP=new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
        for (Class<?> aClass : controllerClassSet) {
            Method[] methods=aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                    String requestPath=requestMapping.value();
                    String type=requestMapping.type();
                    Request request=new Request(type,requestPath);
                    Handler handler=new Handler(aClass,method);
                    REQUEST_HANDLER_MAP.put(request,handler);
                }
            }
        }
    }
    public static Handler getHandler(String requestMethod,String requestPath){
        Request request=new Request(requestMethod,requestPath);
        return REQUEST_HANDLER_MAP.get(request);
    }
}
