package org.love_156.document.entity;

import java.util.Date;

public class Edit {
    private Article article;
    public Integer getID(){
        return article.getDocumentId();
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
