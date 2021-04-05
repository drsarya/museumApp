package com.example.museums.view.fragments.museum.exhibit.editExhibit;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EditExhibitViewModel extends ViewModel {

    private EditExhibitRepository repository = EditExhibitRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ExistingExhibit> getLiveDataUpdateExhibit(Author author, String name, String description, String dateOfCreate, Integer id, File bitmap) {
        isLoading.setValue(true);
        ExistingExhibit existingExhibit = new ExistingExhibit(author, name, description, dateOfCreate, id);
        return repository.updateExhibit(existingExhibit, bitmap);
    }

    public LiveData<List<Author>> getLiveDataAuthorList() {
        return repository.getAllAuthors();
    }
}
