package org.gulanthys.user.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello() {
        String hello = "hello";
        return hello;
    }
}
