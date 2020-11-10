package com.example.services.interfaces;

public interface UserFacade {
    long getUser(String login, String password);

    long insertUser(String login, String password);

}
