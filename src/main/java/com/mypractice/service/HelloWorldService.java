package com.mypractice.service;

import jakarta.inject.Singleton;

@Singleton
public class HelloWorldService {
    public String getMessage(){
        return "Hello World Service";
    }
}
