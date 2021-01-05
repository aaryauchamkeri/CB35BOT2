package com.aarya.model;

public class JsonMessage {

    public String author;
    public String messageContent;
    public String url;
    // u.getAvatar().getUrl();
    // public JsonMessage(String author, String content){
    //     this.author = author;
    //     this.messageContent = content;
    // }
    
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

    // @Override
    // public String toString(){
    //     return "{ \"author\" : \"" + author + "\", \"content\" : " + messageContent + "} ";
    // }
}
