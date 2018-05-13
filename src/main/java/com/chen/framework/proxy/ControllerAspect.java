package com.chen.framework.proxy;

import com.chen.framework.annotation.Aspect;
import com.chen.framework.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Aspect(Controller.class)
@Slf4j
public class ControllerAspect extends AspectProxy{
    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        log.debug("-------------begin--------------");
        begin=System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        log.debug(String.format("time:%dms",System.currentTimeMillis()-begin));
        log.debug("-------------end---------------");
    }
}
