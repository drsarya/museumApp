package com.example.museums.API.models;

import lombok.Data;

@Data
public class AnswerModel {

    private final String message;

    public AnswerModel(String message) {
        this.message = message;
    }

 }
