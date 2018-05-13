package com.chen.framework.bean;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Param {
    Map<String,Object> paramMap;

    public long getLong(String name){
        return Long.parseLong((String)paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }


}
