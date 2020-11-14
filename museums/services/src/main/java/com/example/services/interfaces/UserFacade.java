package com.example.services.interfaces;

import com.example.services.models.User;

public interface UserFacade {
    User getUser(String login, String password);

    long insertUser(String login, String password);

}
