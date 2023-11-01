package org.gulanthys.system.controller;

import org.community.common.Constants;
import org.community.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @PostMapping("/hello")
    public Result<?> Hello() {
        String hello = "hello";
        return Result.buildResult(Constants.Status.OK, hello, hello);
    }
}
