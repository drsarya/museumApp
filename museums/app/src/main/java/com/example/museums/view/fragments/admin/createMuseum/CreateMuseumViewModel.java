package com.example.museums.view.fragments.admin.createMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;

public class CreateMuseumViewModel  extends ViewModel {
    private CreateMuseumRepository repository = CreateMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public CreateMuseumViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getLiveDataUser(String name, String address, String login ) {
        return repository.createMuseum(name, address, login );
    }
}