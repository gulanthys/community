package org.love_156.document.controller;


import org.community.common.Constants;
import org.community.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @GetMapping("/visit")
    public Result<?> visit(){
        String test = "This is a Document";
        return Result.buildResult(Constants.Status.OK,test);
    }
    @GetMapping("/edit")
    public Result<?> edit(){
        String s = "Welcome Editor";
        return Result.buildResult(Constants.Status.OK,s);
    }
}
