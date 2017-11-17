package com.concept.bean;

public class Dictionary {

    private int id;

    private String initial;

    private String title;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Dictionary(int id, String initial, String title, String content) {
        super();
        this.id = id;
        this.initial = initial;
        this.title = title;
        this.content = content;
    }

    public Dictionary() {
        super();
    }

}
