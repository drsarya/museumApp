package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserUpdate extends NewUser{
    String newPassword;
    public UserUpdate(String login, String password, String newPassword) {
        super(login, password);
        this.setNewPassword(newPassword);
    }


}
