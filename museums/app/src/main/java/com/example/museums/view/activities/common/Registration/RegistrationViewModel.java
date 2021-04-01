package com.example.museums.view.activities.common.Registration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;

public class RegistrationViewModel extends ViewModel {

    private RegistrationRepository authorizationRepository = RegistrationRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public RegistrationViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<OkModel> getLiveDataUser(String login, String password) {
        return authorizationRepository.createUser(login, password);
    }

}
