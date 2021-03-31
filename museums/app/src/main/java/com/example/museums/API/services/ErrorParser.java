package com.example.museums.API.services;

import com.example.museums.API.models.ErrorModel;
import com.google.gson.Gson;

import retrofit2.HttpException;

public class ErrorParser {
    static Gson gson = null;

    public static String getMessage(Throwable e) {
        if (gson == null) {
            gson = new Gson();
        }
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ErrorModel message = gson.fromJson(httpException.response().errorBody().charStream(), ErrorModel.class);
            return message.getMessage();
        }
        return "";
    }
}
