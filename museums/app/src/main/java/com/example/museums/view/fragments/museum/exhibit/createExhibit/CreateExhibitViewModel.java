package com.example.museums.view.fragments.museum.exhibit.createExhibit;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.CreateExhibitionRepository;

import java.io.IOException;
import java.util.List;

public class CreateExhibitViewModel extends ViewModel {

    private CreateExhibitRepository repository = CreateExhibitRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public CreateExhibitViewModel() {
        super();

    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ExistingExhibit> getLiveDataCreateExhibit(Author author, String name, String description, String dateOfCreate, Integer exhibitionId, Bitmap bitmap) throws IOException {
        isLoading.setValue(true);
        BaseExhibit baseExhibit = new BaseExhibit(author, name, description, dateOfCreate, exhibitionId);
        return repository.createExhibit(baseExhibit, bitmap);
    }
    public LiveData<List<Author>> getLiveDataAuthorList(){
        return repository.getAllAuthors();
    }
}
