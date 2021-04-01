package com.example.museums.view.fragments.admin.editMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseumRepository;

public class DialogEditMuseumViewModel extends ViewModel {
    private DialogEditMuseumRepository repository = DialogEditMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DialogEditMuseumViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<OkModel> getLiveData(String name, String address, String login) {
        return repository.editMuseum(name, address, login);
    }
}
