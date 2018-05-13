package com.chen.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@AllArgsConstructor
@Getter
public class Handler {
    private Class<?> controllerClass;

    private Method requestMethod;

}
