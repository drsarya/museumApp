package com.example.museums.view.fragments.admin.allMuseums;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.museum.ExistingMuseum;

import java.util.List;

public class AllMuseumsViewModel  extends ViewModel {
    private AllMuseumsRepository repository = AllMuseumsRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public AllMuseumsViewModel() {
        super();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<ExistingMuseum>> getLiveDataUser( ) {
        isLoading.setValue(true);
        return repository.allMuseums( );
    }
}