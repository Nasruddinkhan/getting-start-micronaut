package com.mypractice.service.impl;

import com.mypractice.service.HelloWorldService;
import jakarta.inject.Singleton;

@Singleton
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String getMessage(){
        return "Hello World Service";
    }
}
