package com.example.museums.API.services;

import com.example.museums.API.models.ErrorModel;
import com.google.gson.Gson;

import retrofit2.HttpException;
import retrofit2.Response;

public class ErrorParser {
    static Gson gson = null;

    public static String getMessage(Response e) {
        if (gson == null) {
            gson = new Gson();
        }

        ErrorModel[] message = gson.fromJson(e.errorBody().charStream(), ErrorModel[].class);
        String str = "";
        for (int i = 0; i < message.length; i++) {
            str += message[i].getMessage() + "\n";
        }

        return str;
    }
}
