package org.love_156.document.service;

import org.community.common.Result;
import org.love_156.document.entity.Article;

public interface EditService {
    boolean CreateArticle(Article article,int CreatorID);
    Article Visit(int ArticleID);
    Result<?> Edit();
    Result<?> EditUpdate();
    Result<?> EditAbandon();
}
