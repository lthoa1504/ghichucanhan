package com.example.ghichucanhan.db;

public class Category {
    int idCategory;

    public Category(int idCategory, String nameCategory, String date) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.date = date;
    }

    String nameCategory;
    String date;

    public Category() {
    }

    public Category(String nameCategory, String date) {
        this.nameCategory = nameCategory;
        this.date = date;
    }



    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }






}
