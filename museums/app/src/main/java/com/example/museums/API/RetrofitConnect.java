package com.example.museums.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnect {
    private static Retrofit retrofitConnect = null;

    public static Retrofit getRetrofitConnect() {
        if (retrofitConnect == null) {
            retrofitConnect = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/api/")
                    .addConverterFactory(GsonConverterFactory.create())

                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitConnect;
    }
}
