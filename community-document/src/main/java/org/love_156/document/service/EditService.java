package org.love_156.document.service;

import org.community.common.Result;
import org.love_156.document.entity.Article;

public interface EditService {
    Result<?> CreateArticle(Article article);
    Result<?> Edit();
    Result<?> Visit();
    Result<?> EditUpdate();
    Result<?> EditAbandon();
}
