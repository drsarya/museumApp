package com.example.museums.view.fragments.user.likedExhibits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.util.List;

public class LikedExhibitsViewModel extends ViewModel {
    private LikedExhibitsRepository repository = LikedExhibitsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibits = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoadingExhibits() {
        return isLoadingExhibits;
    }

    public LiveData<List<ExistingExhibit>> getExhibitsLiveData(Integer id) {
        isLoadingExhibits.setValue(true);
        return repository.getExhibits(id);
    }
}
