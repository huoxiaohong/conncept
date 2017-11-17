package com.concept.bean;

public class Articles {
    private int id;

    private String title;

    private String date;

    private String type;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Articles(int id, String title, String date, String type, String status) {
        super();
        this.id = id;
        this.title = title;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public Articles() {
        super();

    }

}
