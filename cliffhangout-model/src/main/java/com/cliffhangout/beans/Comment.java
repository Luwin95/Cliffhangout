package com.cliffhangout.beans;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private int id;
    private String content;
    private List<Comment> children = new ArrayList<Comment>();
    private Comment parent;
    private User author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public void addChild(Comment child){
        this.children.add(child);
    }

    public void removeChild(Comment comment)
    {
        this.children.remove(comment);
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }


}
