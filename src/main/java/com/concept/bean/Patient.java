package com.concept.bean;

public class Patient {

    private String number;

    private String name;

    private String status;

    private String date;

    private String inicheck;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInicheck() {
        return inicheck;
    }

    public void setInicheck(String inicheck) {
        this.inicheck = inicheck;
    }

    public Patient() {
    }

    public Patient(String number, String name, String status, String date, String inicheck) {
        super();
        this.number = number;
        this.name = name;
        this.status = status;
        this.date = date;
        this.inicheck = inicheck;
    }

}
