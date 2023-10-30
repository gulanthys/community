package org.love_156.document.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.Null;
import org.community.common.Result;
import org.love_156.document.entity.Article;
import org.love_156.document.mapper.ArticleMapper;
import org.love_156.document.service.EditService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EditServiceImpl implements EditService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public boolean CreateArticle(Article article) {
        //收到文章信息后直接导入数据库
        log.info("系统正在添加文章");
        articleMapper.insert(article);
        return true;
    }

    @Override
    public Article Visit(int ArticleID) {
        //通过文章id从数据库中搜索文章
        log.info("系统正在搜索文章");
        Article article = articleMapper.selectById(ArticleID);
        if(article.getStatus() == 1)
            return article;
        else
            return null;
    }

    @Override
    public Result<?> Edit() {
        return null;
    }

    @Override
    public Result<?> EditUpdate() {
        return null;
    }

    @Override
    public Result<?> EditAbandon() {
        return null;
    }
}
