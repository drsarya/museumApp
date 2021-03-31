package com.example.museums.API.models;

import lombok.Data;

@Data
public class OkModel {

    private final String message;

    public OkModel(  String message) {
        this.message = message;
    }

 }
