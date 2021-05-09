package com.example.museums.view.fragments.user.exhibits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.museum.ExistingMuseum;

import java.util.List;

public class ExhibitsViewModel extends ViewModel {
    private ExhibitsRepository repository = ExhibitsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibits = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoadingExhibits() {
        return isLoadingExhibits;
    }

    public LiveData<List<ExistingExhibit>> getExhibitsLiveData() {
        isLoadingExhibits.setValue(true);
        return repository.getExhibits();
    }

    public LiveData<List<ExistingExhibit>> getMuseumExhibitsLiveData(Integer id) {
        return repository.getMuseumExhibits(id);
    }

    public LiveData<List<ExistingMuseum>> getMuseums() {
        return repository.getMuseums();
    }
}
