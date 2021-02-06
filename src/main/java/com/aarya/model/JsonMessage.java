package com.aarya.model;

public class JsonMessage {

    private String author;
    private String messageContent;
    private String url;
    private int x;
    
    public JsonMessage(String author, String content, String url){
        this.author = author;
        this.messageContent = content;
        this.url = url;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.messageContent;
    }

    public String getUrl(){
        return this.url;
    }

}
