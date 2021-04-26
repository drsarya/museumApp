package com.example.museums.view.activities.common.Registration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;

public class RegistrationViewModel extends ViewModel {

    private RegistrationRepository authorizationRepository = RegistrationRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

  

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getLiveDataUser(String login, String password) {
        isLoading.setValue(true);
        return authorizationRepository.createUser(login, password);
    }

}
