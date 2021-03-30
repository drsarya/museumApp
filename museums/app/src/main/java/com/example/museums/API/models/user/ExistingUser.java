package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ExistingUser extends NewUser {
    long id;

    public ExistingUser(String login, String password) {
        super(login, password);
    }
}
