package com.example.ghichucanhan.db;

public class Status {
    public Status() {
    }

    int idStatus;
    String nameStatus;
    String date;

    public Status(int idStatus, String nameStatus, String date) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
        this.date = date;
    }


    public Status(String nameStatus, String date) {
        this.nameStatus = nameStatus;
        this.date = date;
    }


    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
