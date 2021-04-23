package com.example.museums.view.fragments.admin.editMuseum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.museum.ExistingMuseum;

public class DialogEditMuseumViewModel extends ViewModel {
    private DialogEditMuseumRepository repository = DialogEditMuseumRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DialogEditMuseumViewModel() {
        super();

    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getLiveData(String name, String address, Integer id) {
        isLoading.setValue(true);
        return repository.editMuseum(name, address, id);
    }

    public LiveData<ExistingMuseum> getLiveDataMuseum(Integer id) {
        return repository.getMuseum(id);
    }

    public LiveData<AnswerModel> getLiveDataDeleteMuseum(Integer id) {
        return repository.deleteMuseum(id);
    }

    public LiveData<AnswerModel> getLiveDataLockMuseum(Integer id) {
        return repository.lockMuseum(id);
    }
    public LiveData<AnswerModel> getOwner(Integer id) {
        return repository.getOwner(id);
    }
}
