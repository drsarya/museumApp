package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserUpdate;
import com.example.museums.API.services.api.UserService;
import com.example.museums.view.activities.common.Registration.RegistrationRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogUpdatePassRepository {
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

    public MutableLiveData<OkModel> updatePassword(UserUpdate existingUser) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        userService.updateUserPassword(existingUser)
                .enqueue(new Callback<OkModel>() {
                    @Override
                    public void onResponse(Call<OkModel> call, Response<OkModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<OkModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
