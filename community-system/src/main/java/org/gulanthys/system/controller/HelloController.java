package org.gulanthys.system.controller;


import org.community.common.Constants;
import org.community.common.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @PreAuthorize("hasAuthority('hello')")
    @GetMapping("/hello")
    public Result<?> Hello() {
        String hello = "hello";
        return Result.buildResult(Constants.Status.OK, hello, hello);
    }
}
