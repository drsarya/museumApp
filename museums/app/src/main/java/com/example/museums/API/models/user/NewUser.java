package com.example.museums.API.models.user;

import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.museum.Museum;

import io.reactivex.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewUser {
   private String login;

    @Nullable
    private  String password;

    private  RoleEnum role;
    @Nullable
    private Integer museumId;

    public NewUser(String login,  String password) {
        this.login = login;
        this.password = password;
    }
}
