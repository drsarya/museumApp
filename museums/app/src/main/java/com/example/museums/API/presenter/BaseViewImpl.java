package com.example.museums.API.presenter;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public abstract class BaseViewImpl implements BasePresenter.View {
    Context context;
    ProgressBar progressBar;

    public BaseViewImpl(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    abstract public <T> void showData(T data);

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
