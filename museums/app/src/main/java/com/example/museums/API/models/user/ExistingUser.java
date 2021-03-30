package com.example.museums.API.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExistingUser extends NewUser {
    long id;
}
