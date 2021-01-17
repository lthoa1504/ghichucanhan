package com.example.ghichucanhan.db;




public class Note {
    int id;
    String name;
    String date;
    int idStatus;
    int idCategory;
    int idPriority;
    int idUser;
    String planDate;





    public Note(int id, String name, String date, String planDate, int idUser, int idCategory, int idStatus, int idPriority) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.idStatus = idStatus;
        this.idCategory = idCategory;
        this.idPriority = idPriority;
        this.idUser = idUser;
        this.planDate = planDate;
    }

    public Note(String name, String date, String planDate, int idUser, int idStatus, int idCategory, int idPriority) {
        this.name = name;
        this.date = date;
        this.idStatus = idStatus;
        this.idCategory = idCategory;
        this.idPriority = idPriority;
        this.idUser = idUser;
        this.planDate = planDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdPriority() {
        return idPriority;
    }

    public void setIdPriority(int idPriority) {
        this.idPriority = idPriority;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }











}
