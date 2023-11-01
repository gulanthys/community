package org.love_156.document.service;

import org.community.common.Result;
import org.love_156.document.entity.Article;
import org.springframework.web.bind.annotation.RequestParam;

public interface EditService {
    boolean CreateArticle(Article article,int creatorId);
    Article Visit(int articleId);
    boolean Edit(int articleId,int editorId);
    boolean EditUpdate(Article article,int editorId);
    Result<?> EditAbandon();
}
