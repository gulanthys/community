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
@RequestMapping("/article")
public class EditController {

    @Resource
    private EditService editService;

    /**
     *  文章创建
     * @param article 文章id
     * @param creatorId 作者id
     * @return 是否创建成功
     */
    @PostMapping("/creat")
    public Result<?> CreateDocument(@RequestBody Article article,@RequestParam("creatorId") int creatorId){
        log.info("用户请求创建文章");
        log.info("文章标题是"+article.getTitle());
        boolean createArticle = editService.CreateArticle(article,creatorId);
        if(createArticle){
            return Result.buildResult(Constants.Status.OK,"用户创建文章成功");
        }else {
            return Result.buildResult(Constants.Status.BAD_REQUEST,"用户创建文章失败");
        }
    }

    /**
     * 提起文章
     * @param articleId 文章id
     * @return 文章本体
     */
    @GetMapping("/visit")
    public Result<?> Visit(@RequestParam("articleId") int articleId ){
        log.info("用户正打算在浏览");
        Article article = editService.Visit(articleId);
        if (article == null){
            return Result.buildResult(Constants.Status.NOT_FOUND,"未找到对应文章");
        }else {
            return Result.buildResult(Constants.Status.OK,article);
        }
    }
    /**
     * 查询文章编辑权限
     * @param articleId 文章id
     * @param editorId 编者id
     * @return 是否拥有权限
     */
    @GetMapping("/edit")
    public Result<?> Edit(@RequestParam("articleId") int articleId,@RequestParam("editorId") int editorId){
        log.info("用户申请编辑权限");
        boolean per = editService.Edit(articleId, editorId);
        if(per){
            return Result.buildResult(Constants.Status.OK,"用户申请成功");
        }else {
            return Result.buildResult(Constants.Status.UNAUTHORIZED,"用户申请失败");
        }
    }
    @PostMapping("/edit/update")
    public Result<?> EditUpdate(@RequestBody Article article,@RequestParam("editorId") int editorId){
        log.info("开始更新文章,id为"+article);
        log.info("开始更新文章,标题为"+article.getTitle());
        boolean update = editService.EditUpdate(article,editorId);
        log.info("更新操作完成");
        if(update){
            return Result.buildResult(Constants.Status.OK,"用户更新成功");
        }else {
            return Result.buildResult(Constants.Status.UNAUTHORIZED,"用户更新失败");
        }
    }
    @GetMapping("/edit/invite")
    public Result<?> EditInvite(@RequestParam("articleId") int articleId,@RequestParam("editorId") int editorId){
        log.info("用户申请编辑权限");

        return Result.buildResult(Constants.Status.OK);
    }
    @GetMapping("/edit/delete")
    public Result<?> EditDelete(@RequestParam("articleId") int articleId,@RequestParam("editorId") int editorId){
        String s = "删除本篇编辑";

        return Result.buildResult(Constants.Status.OK,s);
    }
}
