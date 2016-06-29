package com.example.mbmbmb.moviesapp;

/**
 * Created by mbmbmb on 4/27/2016.
 */
public class Review {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String id;
    String author;
    String url;
    String content;
}
