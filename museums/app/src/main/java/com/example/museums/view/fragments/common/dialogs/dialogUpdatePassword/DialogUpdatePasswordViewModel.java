package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.UserUpdate;

public class DialogUpdatePasswordViewModel extends ViewModel {

    private DialogUpdatePassRepository repository = DialogUpdatePassRepository.getInstance();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DialogUpdatePasswordViewModel() {
        super();
        isLoading.setValue(true);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<OkModel> getLiveDataUpdatePassword(Integer id, String oldPassword, String newPassword) {
        UserUpdate existingUser = new UserUpdate(id, null, oldPassword, newPassword);
        return repository.updatePassword(existingUser);
    }

}
