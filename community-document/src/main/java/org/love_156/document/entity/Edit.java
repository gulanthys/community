package org.love_156.document.entity;

public class Edit {
    private Document document;

    public Integer getID(){ return document.getDocumentID(); }

    public String getText(){return document.getText();}
    public String getTitle(){return document.getTitle();}


}
