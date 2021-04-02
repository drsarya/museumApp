package com.example.museums.view.activities.common.RegistrationMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;

public class RegistrationMuseumViewModel extends ViewModel {
    private RegistrationMuseumRepository authorizationRepository = RegistrationMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public RegistrationMuseumViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<OkModel> getLiveDataUser(Integer idCode, String login, String password) {
        return authorizationRepository.createMuseum(idCode, login, password);
    }
}