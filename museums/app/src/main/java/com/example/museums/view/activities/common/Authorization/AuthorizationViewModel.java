package com.example.museums.view.activities.common.Authorization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.user.ExistingUser;

public class AuthorizationViewModel extends ViewModel {

    private AuthorizationRepository authorizationRepository = AuthorizationRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

 
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ExistingUser> getLiveDataUser(String login, String password) {
        isLoading.setValue(true);
        return authorizationRepository.getUser(login, password);
    }

}
