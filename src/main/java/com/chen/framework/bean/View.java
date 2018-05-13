package com.chen.framework.bean;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class View {

    private String path;

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

}
