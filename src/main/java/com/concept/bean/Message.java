package com.concept.bean;

public class Message {
    private int number;

    private String name;

    private String symptom;

    private String date;

    private String inicheck;

    private String status;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Message(int number, String name, String symptom, String date, String inicheck, String status) {
        super();
        this.number = number;
        this.name = name;
        this.symptom = symptom;
        this.date = date;
        this.inicheck = inicheck;
        this.status = status;
    }

    public Message() {
        super();
    }

    @Override
    public String toString() {
        return "Message [number=" + number + ", name=" + name + ", symptom=" + symptom + ", date=" + date
                + ", inicheck=" + inicheck + ", status=" + status + "]";
    }

}
