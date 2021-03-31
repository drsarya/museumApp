package com.example.museums.API.presenter;

import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.services.repository.UserRepos;
import com.example.museums.API.services.repository.impl.UserReposImpl;

public abstract class BasePresenterImpl implements BasePresenter.Presenter {
        public BasePresenter.View view;

        @Override
        public <T> void onSuccess(T value) {
            view.hideProgress();
            view.showData(value);
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showError(error);
        }
        @Override
        public void attach(BasePresenter.View view) {
            this.view = view;
        }

        @Override
        public void detach() {
            this.view = null;
        }
        protected abstract void loadData();



 }
