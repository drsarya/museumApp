package com.example.museums.view.fragments.museum.museumExhibits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.util.List;

public class MuseumExhibitsViewModel extends ViewModel {
    private MuseumExhibitsRepository repository = MuseumExhibitsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibits = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingDeleteExhibit = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoadingExhibits() {
        return isLoadingExhibits;
    }

    public MutableLiveData<Boolean> getIsLoadingDeleteExhibit() {
        return isLoadingDeleteExhibit;
    }

    public LiveData<List<ExistingExhibit>> getExhibitsLiveData(Integer id) {
        isLoadingExhibits.setValue(true);
        return repository.getExhibits(id);
    }

    public LiveData<AnswerModel> deleteExhibitLiveData(Integer id) {
        isLoadingDeleteExhibit.setValue(true);
        return repository.deleteExhibit(id);
    }
}
