package com.example.museums.view.activities.common.RegistrationMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;

public class RegistrationMuseumViewModel extends ViewModel {
    private RegistrationMuseumRepository authorizationRepository = RegistrationMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getLiveDataUser(Integer idCode, String login, String password) {
        isLoading.setValue(true);
        return authorizationRepository.createMuseum(idCode, login, password);
    }
}
