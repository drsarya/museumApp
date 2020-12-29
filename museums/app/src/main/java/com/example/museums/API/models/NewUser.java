package com.example.museums.API.models;

public class NewUser {


    public String login;

    public NewUser(String login, String password, boolean type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public String password;
    //true - admin
    //false обычный пользователь

    public boolean type;


}
