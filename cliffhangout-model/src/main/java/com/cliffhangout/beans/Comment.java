package com.cliffhangout.beans;

public class Comment {

    private int id;
    private String content;
    private Comment parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User author;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }





}
