package com.example.museums.view.fragments.museum.exhibition.createExhibition;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.models.museum.ShortInfoMuseum;

import java.io.File;

public class CreateExhibitionViewModel extends ViewModel {
    private CreateExhibitionRepository repository = CreateExhibitionRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ExistingExhibition> liveDataCreateExhibition(Integer museumId, String name, String description,
                                                                 String dateOfStart, String dateOfEnd, File bitmap) {
        isLoading.setValue(true);
        BaseExhibition baseExhibition = new BaseExhibition(new ShortInfoMuseum(museumId), name, description, dateOfStart, dateOfEnd);
        return repository.createExhibition(baseExhibition, bitmap);
    }
}
