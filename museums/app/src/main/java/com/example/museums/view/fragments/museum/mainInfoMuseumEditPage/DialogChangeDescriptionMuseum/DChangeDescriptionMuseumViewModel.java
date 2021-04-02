package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseumRepository;

public class DChangeDescriptionMuseumViewModel extends ViewModel {
    private DChangeDescriptionMuseumRepo repository = DChangeDescriptionMuseumRepo.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DChangeDescriptionMuseumViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<OkModel> getUpdateDescriptionLiveData(Integer museumId, String description) {
        UpdatableMuseum updatableMuseum = new UpdatableMuseum();
        updatableMuseum.setDescription(description);
        updatableMuseum.setId(  museumId);
        return repository.updateDescription(updatableMuseum);
    }
}
