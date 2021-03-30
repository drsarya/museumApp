package com.example.museums.API.presenter;

import android.content.Context;

import java.util.List;

public interface BasePresenter {
    interface View {
        <T> void showData(T data);
        void showError(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        <T>   void onSuccess( T value );
        void onError(String error);
        void loadData( );
    }
}
