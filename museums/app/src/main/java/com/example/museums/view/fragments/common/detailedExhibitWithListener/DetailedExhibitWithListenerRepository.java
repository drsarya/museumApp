package com.example.museums.view.fragments.common.detailedExhibitWithListener;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.LikeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedExhibitWithListenerRepository {
    private static DetailedExhibitWithListenerRepository repository;
    private LikeService likeService;

    public static DetailedExhibitWithListenerRepository getInstance() {
        if (repository == null) {
            repository = new DetailedExhibitWithListenerRepository();
        }
        return repository;
    }

    public DetailedExhibitWithListenerRepository() {
        likeService = RetrofitConnect.createRetrofitConnection(LikeService.class);
    }

    public MutableLiveData<BaseLike> getUserLike(UserLike userLike) {
        MutableLiveData<BaseLike> newsData = new MutableLiveData<>();
        likeService.getLikeByUser(userLike)
                .enqueue(new Callback<BaseLike>() {
                    @Override
                    public void onResponse(Call<BaseLike> call, Response<BaseLike> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }else {
                            newsData.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseLike> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<String> getCountOfLikeOnExhibition(BaseLike baseLike) {
        MutableLiveData<String> newsData = new MutableLiveData<>();
        likeService.getLikesByArtId(baseLike)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body().getMessage());
                        }else {
                            newsData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<AnswerModel> insertLike(UserLike userLike) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        likeService.createLike(userLike)
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
