package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdate extends NewUser{
    String newPassword;
}
