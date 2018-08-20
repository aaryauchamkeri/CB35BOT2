package com.aarya.model;

public class JsonMessage {

    private String author;
    private String messageContent;
    private String url;
    private String id;
    
    public JsonMessage(String author, String content, String url){
        this.author = author;
        this.messageContent = content;
        this.url = url;
    }

    public JsonMessage(String author, String content, String url, String id){
        this.author = author;
        this.messageContent = content;
        this.url = url;
        this.id = id;
    }

    public String getId(){
        return id;
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
