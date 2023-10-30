package org.love_156.document.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Constants;
import org.community.common.Result;
import org.love_156.document.entity.Article;
import org.love_156.document.service.EditService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/blog")
public class EditController {

    @Resource
    private EditService editService;

    @PostMapping("/creat")
    public Result<?> CreateDocument(@RequestBody Article article,@RequestParam("CreatorID") int CreatorID){
        log.info("用户请求创建文章");
        log.info("文章标题是"+article.getTitle());
        boolean createArticle = editService.CreateArticle(article,CreatorID);
        if(createArticle){
            return Result.buildResult(Constants.Status.OK,"用户创建文章成功");
        }else {
            return Result.buildResult(Constants.Status.BAD_REQUEST,"用户创建文章失败");
        }
    }
    @GetMapping("/visit")
    public Result<?> Visit(@RequestParam("ArticleID") int ArticleID ){
        log.info("用户正打算在浏览");
        Article article = editService.Visit(ArticleID);
        if (article == null){
            return Result.buildResult(Constants.Status.NOT_FOUND,"未找到对应文章");
        }else {
            return Result.buildResult(Constants.Status.OK,article);
        }
    }
    @GetMapping("/edit")
    public Result<?> Edit(){
        String s = "用户正在编辑";
        return Result.buildResult(Constants.Status.OK,s);
    }
    @GetMapping("/edit/update")
    public Result<?> EditUpdate(@RequestBody Article article,@RequestParam("EditorID") int EditorID){
        String s = "更新文章";
        return Result.buildResult(Constants.Status.OK,s);
    }
    @GetMapping("/edit/abandon")
    public Result<?> EditAbandon(){
        String s = "放弃本次编辑";
        return Result.buildResult(Constants.Status.OK,s);
    }
}
