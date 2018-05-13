package com.chen.framework.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public abstract class AspectProxy implements Proxy {


    @Override
    public Object doProxy(ProxyChain proxyChain) throws  Throwable{
        Object result=null;
        Class<?> cls=proxyChain.getTargetClass();
        Method method=proxyChain.getTargetMethod();
        Object[] params=proxyChain.getMethodParams();
        begin();

        try{
            if(intercept(cls,method,params)){
                before(cls,method,params);
                result=proxyChain.doProxyChain();
                after(cls,method,params,result);
            }else{
                result=proxyChain.doProxyChain();
            }
        }catch (Throwable e ){
            log.error("something error",e);
            error(cls,method,params,e);
            throw e;
        }finally {
            end();
        }
        return null;
    }

    public void begin(){

    }

    public boolean intercept(Class<?> cls,Method method,
                             Object[] params){
        return true;
    }

    public void before(Class<?> cls,Method method,Object[] params){

    }

    public void after(Class<?> cls,Method method,Object[] params,Object
                      result){

    }

    public void error(Class<?> cls ,Method method,Object[] params ,Throwable e
                      ){

    }

    public void end(){

    }
}
