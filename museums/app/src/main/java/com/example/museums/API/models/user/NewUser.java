package com.example.museums.API.models.user;

import com.example.museums.API.models.enums.RoleEnum;

import io.reactivex.annotations.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUser {
    public String login;

    @Nullable
    public  String password;

    public   RoleEnum role;
    @Nullable
    public Integer museumId;

    public NewUser(String login, String password ) {
        this.login = login;
        this.password = password;
    }
    public NewUser(String login, String password, RoleEnum roleEnum)  {
        this.login = login;
        this.password = password;
        this.role = roleEnum;
    }

}
