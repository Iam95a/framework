package com.chen.framework.proxy;

import com.chen.framework.annotation.Transaction;
import com.chen.framework.helper.DataBaseHelper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class TransactionProxy implements  Proxy {


    private static final ThreadLocal<Boolean>
        FLAG_HOLDER=new ThreadLocal<Boolean>(){
        @Override
        protected  Boolean initialValue(){
            return false;
        }
    };
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag=FLAG_HOLDER.get();
        Method method=proxyChain.getTargetMethod();
        if(!flag&&method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try{
                DataBaseHelper.beginTransaction();
                log.debug("transaction begin");
                result=proxyChain.doProxyChain();
                DataBaseHelper.commitTransaction();
                log.debug("transcation commited");
            }catch (Exception e){
                DataBaseHelper.rollbackTransaction();
                log.debug("transcation rollbacked");
                throw e;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            result=proxyChain.doProxyChain();
        }
        return result;
    }


}
