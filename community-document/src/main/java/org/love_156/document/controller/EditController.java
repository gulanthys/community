package org.love_156.document.controller;

import org.community.common.Constants;
import org.community.common.Result;
import org.love_156.document.entity.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class EditController {
    @GetMapping("/creat")
    public Result<?> CreateDocument(@RequestBody Document document){
        String s = "用户创建文章";
        return Result.buildResult(Constants.Status.OK,s);
    }
    @GetMapping("/visit")
    public Result<?> Visit(){
        String test = "用户正在浏览";
        return Result.buildResult(Constants.Status.OK,test);
    }
    @GetMapping("/edit")
    public Result<?> Edit(){
        String s = "用户正在编辑";
        return Result.buildResult(Constants.Status.OK,s);
    }
    @GetMapping("/edit/update")
    public Result<?> EditUpdate(){
        String s = "更新文章";
        return Result.buildResult(Constants.Status.OK,s);
    }
    @GetMapping("/edit/abandon")
    public Result<?> EditAbandon(){
        String s = "放弃本次编辑";
        return Result.buildResult(Constants.Status.OK,s);
    }
}
