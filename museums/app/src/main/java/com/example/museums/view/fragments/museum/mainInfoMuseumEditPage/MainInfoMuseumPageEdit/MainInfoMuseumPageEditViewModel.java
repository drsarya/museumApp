package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.museum.ExistingMuseum;

public class MainInfoMuseumPageEditViewModel extends ViewModel {
    private MainInfoPageEditMuseumRepository repository = MainInfoPageEditMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MainInfoMuseumPageEditViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ExistingMuseum> getMuseumInfo(Integer id ) {
        return repository.getMuseumInfo(id);
    }

}
