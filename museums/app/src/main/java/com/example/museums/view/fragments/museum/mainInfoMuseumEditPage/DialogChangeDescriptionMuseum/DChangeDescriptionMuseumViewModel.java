package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.UpdatableMuseum;

public class DChangeDescriptionMuseumViewModel extends ViewModel {
    private DChangeDescriptionMuseumRepo repository = DChangeDescriptionMuseumRepo.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getUpdateDescriptionLiveData(Integer museumId, String description) {
        isLoading.setValue(true);
        UpdatableMuseum updatableMuseum = new UpdatableMuseum();
        updatableMuseum.setDescription(description);
        updatableMuseum.setId(museumId);
        return repository.updateDescription(updatableMuseum);
    }
}
