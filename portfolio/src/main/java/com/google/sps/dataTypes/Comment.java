package com.google.sps.data;
public final class Comment {
    public final class Comment {

    private final String name;	    
    private final String messageContent;	  
    private final String imageUrl;


    public Comment(String name, String messageContent, String imageUrl) {
        this.name = name;	        
        this.messageContent = messageContent;	     
        this.imageUrl = imageUrl;
    }

    public Comment(String name, String messageContent) {
        this(name, messageContent, null);
	}
}