package com.example.museums.API.services;

import com.example.museums.API.models.ErrorModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import io.reactivex.internal.util.ErrorMode;
import retrofit2.HttpException;
import retrofit2.Response;

public class ErrorParser {
    static Gson gson = null;

    public static String getMessage(Response e) {
        if (gson == null) {
            gson = new Gson();
        }
        ErrorModel[] message;
           try {
        message = gson.fromJson(e.errorBody().charStream(), ErrorModel[].class);
        } catch (JsonSyntaxException syntaxException) {
            message = new ErrorModel[1];
            message[0] = new ErrorModel(Integer.toString(e.raw().code()),e.raw().body().toString());

        }

        String str = "";
        for (int i = 0; i < message.length; i++) {
            if (message[i].getMessage() != null) {
                str += message[i].getMessage() + "\n";
            }
//
//        }


        } return str;
    }
}
