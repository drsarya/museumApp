package com.example.museums.API.interfaces;


public interface UserFacade {
    void getUser(String login, String password) throws InterruptedException;

    void insertUser(String login, String password, boolean type);

}
