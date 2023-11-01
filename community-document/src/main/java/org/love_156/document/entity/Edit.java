package org.love_156.document.entity;

import jakarta.annotation.Resource;

import java.util.Date;

public class Edit {
    @Resource
    private Article article;
    public Integer getID(){
        return article.getArticleId();
    }
    public String getText(){
        return article.getText();
    }
    public String getTitle(){
        return article.getTitle();
    }
    public Date getCreatDate(){
        return article.getCreatDate();
    }
    public  Date getEditDate(){
        return article.getEditDate();
    }
}
