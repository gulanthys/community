package org.love_156.document.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.Null;
import org.community.common.Result;
import org.love_156.document.entity.Article;
import org.love_156.document.entity.EditPermission;
import org.love_156.document.mapper.ArticleMapper;
import org.love_156.document.mapper.EditPermissionMapper;
import org.love_156.document.service.EditService;
import org.springframework.boot.logging.java.JavaLoggingSystem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Slf4j
@Service
public class EditServiceImpl implements EditService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private EditPermissionMapper editPermissionMapper;

    /**
     *  文章创建
     * @param article 文章id
     * @param creatorId 作者id
     * @return 是否创建成功
     */
    @Override
    public boolean CreateArticle(Article article,int creatorId) {
        //收到文章信息后直接导入数据库
        log.info("系统正在添加文章");
        articleMapper.insert(article);
        EditPermission editPermission = new EditPermission();
        editPermission.setArticleId(article.getArticleId());
        editPermission.setPermission(1);
        editPermission.setUserId(creatorId);
        editPermissionMapper.insert(editPermission);
        return true;
    }

    /**
     * 提起文章
     * @param articleId 文章id
     * @return 文章本体
     */
    @Override
    public Article Visit(int articleId) {
        //通过文章id从数据库中搜索文章
        log.info("系统正在搜索文章");
        Article article = articleMapper.selectById(articleId);
        if(article.getStatus() == 1)
            return article;
        else
            return null;
    }

    /**
     * 查询文章编辑权限
     * @param articleId 文章id
     * @param editorId 编者id
     * @return 是否拥有权限
     */
    @Override
    public boolean Edit(int articleId,int editorId) {
        int Permission = editPermissionMapper.findPermissionByArticleIdAndUserId(articleId, editorId);
        if(Permission == 1||Permission == 2){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 文章更新
     * @param article 文章本体
     * @param editorId 作者id
     * @return 是否修改成功
     */
    @Override
    public boolean EditUpdate(Article article,int editorId) {
        int ArticleId = article.getArticleId();
        int Permission = editPermissionMapper.findPermissionByArticleIdAndUserId(ArticleId, editorId);
        log.info("查询成功");
        if(Permission == 1||Permission == 2){
            QueryWrapper<Article> wrapper = new QueryWrapper<>();
            wrapper.select("*").eq("article_id",article.getArticleId());
            articleMapper.update(article,wrapper);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Result<?> EditAbandon() {
        return null;
    }
}
