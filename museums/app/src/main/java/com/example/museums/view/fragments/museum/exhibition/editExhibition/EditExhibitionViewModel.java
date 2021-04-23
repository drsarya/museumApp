package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.models.museum.ShortInfoMuseum;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EditExhibitionViewModel extends ViewModel {
    private EditExhibitionRepository repository = EditExhibitionRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingEditExhibition = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingDeleteExhibit = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingListExhibits = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoadingEditExhibition() {
        return isLoadingEditExhibition;
    }

    public MutableLiveData<Boolean> getIsLoadingDeleteExhibit() {
        return isLoadingDeleteExhibit;
    }

    public MutableLiveData<Boolean> getIsLoadingListExhibits() {
        return isLoadingListExhibits;
    }

    public LiveData<ExistingExhibition> getLiveDataEditExhibition(Integer exhibitionId, Integer museumId, String name, String description, String dateOfStart, String dateOfEnd, File bitmap) {
        isLoadingEditExhibition.postValue(true);
        ExistingExhibition baseExhibition = new ExistingExhibition(new ShortInfoMuseum(museumId), name, description, dateOfStart, dateOfEnd, exhibitionId);
        return repository.editExhibition(baseExhibition, bitmap);
    }

    public LiveData<AnswerModel> getLiveDataDeleteExhibit(Integer exhibitId) {
        isLoadingDeleteExhibit.postValue(true);
        return repository.deleteExhibit(exhibitId);
    }

    public LiveData<List<ExistingExhibit>> getLiveDataExhibitsFromExhibition(Integer exhibitionId) {
        isLoadingListExhibits.postValue(true);
        return repository.getExhibitsFromExhibition(exhibitionId);
    }
}
