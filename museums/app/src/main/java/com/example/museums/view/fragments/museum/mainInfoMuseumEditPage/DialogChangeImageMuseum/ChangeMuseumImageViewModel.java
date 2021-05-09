package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;

import java.io.File;

public class ChangeMuseumImageViewModel extends ViewModel {
    private ChangeMuseumPhotoRepository repository = ChangeMuseumPhotoRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public ChangeMuseumImageViewModel() {
        super();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getUpdateLiveData(File bitmap, Integer id) {
        isLoading.setValue(true);
        return repository.updateMuseumImage(bitmap, id);
    }

}
