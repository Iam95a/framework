package com.chen.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Request {

    public static final String REQUEST_METHOD_GET="get";
    public static final String REQUEST_METHOD_POST="post";
    /**
     * 请求方法  get/post
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;


}
