package com.example.museums.API.interfaces;



public interface UserFacade {
    void getUser(String login, String password)   ;
    void getUser(String login, String password, String oldpassword )   ;

    void insertUser(String login, String password, boolean type);

    void insertUserMuseum(String login, String password, boolean type);

    void updateUserPassword(String login, String password);


}
