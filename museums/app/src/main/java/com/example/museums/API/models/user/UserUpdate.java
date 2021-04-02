package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class UserUpdate extends ExistingUser {

    String newPassword;

    public UserUpdate(Integer id, String login, String password,  String newPassword) {
        super(id, login, password);
        this.newPassword = newPassword;
    }
}
