package com.example.museums.view.fragments.user.likedExhibits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.view.fragments.user.likedExhibitions.LikedExhibitsRepository;

import java.util.List;

public class LikedExhibitionsViewModel extends ViewModel {
    private LikedExhibitionsRepository repository = LikedExhibitionsRepository.getInstance();
    private MutableLiveData<Boolean> isLoadingExhibits = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLoadingExhibits() {
        return isLoadingExhibits;
    }

    public LiveData<List<ExistingExhibition>> getExhibitionsLiveData(Integer id ) {
        isLoadingExhibits.setValue(true);
        return repository.getExhibitions( id );
    }
}
