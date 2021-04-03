package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;

import java.io.IOException;

public class ChangeMuseumImageViewModel   extends ViewModel {
    private ChangeMuseumPhotoRepository repository = ChangeMuseumPhotoRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public ChangeMuseumImageViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getUpdateLiveData(Bitmap bitmap, Integer id) throws IOException {
        return repository.updateMuseumImage(bitmap, id );
    }

}
