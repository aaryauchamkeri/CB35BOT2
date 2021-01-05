package com.aarya.model;

public class TextChannelInfo {
    
    private String id;
    private String name;

    public TextChannelInfo(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
}
