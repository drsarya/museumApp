package com.example.museums.view.fragments.common.museumInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.ExistingMuseum;

public class MainInfoMuseumViewModel extends ViewModel {

    private MainInfoMuseumRepository repository = MainInfoMuseumRepository.getInstance();

    public MainInfoMuseumViewModel() {
        super();
    }


    public LiveData<ExistingMuseum> getLiveDataMuseumInfo(Integer id) {
        return repository.getMuseumInfo(id);
    }
}
