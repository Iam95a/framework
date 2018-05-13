package com.chen.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Request {
    /**
     * 请求方法  get/post
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;


}
