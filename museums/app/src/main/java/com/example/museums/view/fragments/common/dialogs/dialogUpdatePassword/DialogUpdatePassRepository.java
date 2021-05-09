package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.user.UserUpdate;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogUpdatePassRepository extends DialogFragment {
    private static DialogUpdatePassRepository registrationRepository;

    public static DialogUpdatePassRepository getInstance() {
        if (registrationRepository == null) {
            registrationRepository = new DialogUpdatePassRepository();
        }
        return registrationRepository;
    }

    private UserService userService;

    public DialogUpdatePassRepository() {
        userService = RetrofitConnect.createRetrofitConnection(UserService.class);
    }

    public MutableLiveData<AnswerModel> updatePassword(UserUpdate existingUser) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        userService.updateUserPassword(existingUser)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }else {
                            newsData.setValue(new AnswerModel(ErrorParser.getMessage(response)));
                        }
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
