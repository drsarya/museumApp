package com.example.museums.view.fragments.museum.museumExhibitions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

public class MuseumExhibitionsViewModel extends ViewModel {
    private MuseumExhibitionsRepository repository = MuseumExhibitionsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibitions = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingDeleteExhibition = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoadingExhibitions() {
        return isLoadingExhibitions;
    }

    public MutableLiveData<Boolean> getIsLoadingDeleteExhibition() {
        return isLoadingDeleteExhibition;
    }

    public LiveData<List<ExistingExhibition>> getExhibitionsLiveData(Integer id) {
        isLoadingExhibitions.setValue(true);
        return repository.getExhibitions(id);
    }

    public LiveData<OkModel> deleteExhibitionLiveData(Integer id) {
        isLoadingDeleteExhibition.setValue(true);
        return repository.deleteExhibition(id);
    }
}
