package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserMuseum   {
    Integer idCode   ;
    String password, login;


    public UserMuseum(Integer idCode, String password, String login) {
        this.idCode = idCode;
        this.password = password;
        this.login = login;
    }
}
