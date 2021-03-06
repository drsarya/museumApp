package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.user.UserUpdate;

public class DialogUpdatePasswordViewModel extends ViewModel {

    private DialogUpdatePassRepository repository = DialogUpdatePassRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<AnswerModel> getLiveDataUpdatePassword(Integer id, String oldPassword, String newPassword) {
        isLoading.setValue(true);
        UserUpdate existingUser = new UserUpdate(id, null, oldPassword, newPassword);
        return repository.updatePassword(existingUser);
    }

}
