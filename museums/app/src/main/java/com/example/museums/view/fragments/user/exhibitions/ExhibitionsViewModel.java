package com.example.museums.view.fragments.user.exhibitions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

public class ExhibitionsViewModel extends ViewModel {
    private ExhibitionsRepository repository = ExhibitionsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibits = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoadingExhibits() {
        return isLoadingExhibits;
    }

    public LiveData<List<ExistingExhibition>> getExhibitionsLiveData() {
        isLoadingExhibits.setValue(true);
        return repository.getExhibitions();
    }

}
