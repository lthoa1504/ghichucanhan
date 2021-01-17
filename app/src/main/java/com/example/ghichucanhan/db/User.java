package com.example.ghichucanhan.db;
import  java.io.Serializable;

public class User  implements Serializable {
    int idUser;
    String emailUser;
    String passwordUser;
    String firstnameUser;
    String lastnameUser;

    public User(int idUser, String emailUser, String passwordUser, String firstnameUser, String lastnameUser ) {
        this.idUser = idUser;
        this.firstnameUser = firstnameUser;
        this.lastnameUser = lastnameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;

    }

    public User() {
    }

    public User(String emailUser, String passwordUser, String firstnameUser, String lastnameUser) {
        this.firstnameUser = firstnameUser;
        this.lastnameUser = lastnameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
    }



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getFirstnameUser() {return firstnameUser;}
    public void setFirstnameUser(String firstnameUser){this.firstnameUser = firstnameUser;}

    public String getLastnameUser(){return lastnameUser;}
    public void setLastnameUser(String lastnameUser){this.lastnameUser = lastnameUser;}

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }






}
