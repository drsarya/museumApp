package com.example.museums.API.models;

public class UserUpdate {
    Long id ;
    String login ;
    String newPassword ;
    String password ;

    public UserUpdate(Long getId, String getLogin, String getNewPassword, String getPassword) {
        this.id = getId;
        this.login = getLogin;
        this.newPassword = getNewPassword;
        this.password = getPassword;
    }
}
