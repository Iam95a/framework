package com.chen.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class CodecUtil {
    public static String encodeURL(String source){
        String target;
        try{
            target= URLEncoder.encode(source,"utf8");
        }catch (Exception e ){
            log.error("encode url error ",e);
            throw new RuntimeException( e);
        }
        return target;
    }

    public static String decodeURL(String source){
        String target;
        try{
            target= URLDecoder.decode(source,"utf8");
        }catch (Exception e ){
            log.error("decode from url error ",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
