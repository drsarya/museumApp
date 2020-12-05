package com.example.museums.API.interfaces;


public interface UserFacade {

    /*GET*/
    void getUser(String login, String password);
    void getUser(String login );

    void getUser(String login, String password, String oldpassword);

    /*INSERT*/
    void insertUser(String login, String password, boolean type);

    void insertUserMuseum(String login, String password, boolean type);

    /*UPDATE*/
    void updateUserPassword(String login, String password);


}
