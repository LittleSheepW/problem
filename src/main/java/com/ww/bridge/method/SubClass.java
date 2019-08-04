package com.ww.bridge.method;

public class SubClass implements SuperClass<String> {

    @Override
    public String method(String param) {
        return param;
    }
}