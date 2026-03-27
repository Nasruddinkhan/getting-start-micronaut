package com.mypractice.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloWorld {

    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld(){
        return "Hello World";
    }
}
