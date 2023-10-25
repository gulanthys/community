package org.love_156.document.entity;

import java.util.Date;

public class Edit {
    private Document document;
    public Integer getID(){
        return document.getDocumentId();
    }
    public String getText(){
        return document.getText();
    }
    public String getTitle(){
        return document.getTitle();
    }
    public Date getCreatDate(){
        return document.getCreatDate();
    }
    public  Date getEditDate(){
        return document.getEditDate();
    }
}
