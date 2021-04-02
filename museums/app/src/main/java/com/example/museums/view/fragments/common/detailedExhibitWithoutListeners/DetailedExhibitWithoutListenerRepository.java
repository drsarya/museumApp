package com.example.museums.view.fragments.common.detailedExhibitWithoutListeners;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;
import com.example.museums.API.services.api.LikeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedExhibitWithoutListenerRepository {
    private static DetailedExhibitWithoutListenerRepository repository;
    private LikeService likeService;

    public static DetailedExhibitWithoutListenerRepository getInstance() {
        if (repository == null) {
            repository = new DetailedExhibitWithoutListenerRepository();
        }
        return repository;
    }

    public DetailedExhibitWithoutListenerRepository() {
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
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseLike> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<Integer> getCountOfLikeOnExhibition(BaseLike baseLike) {
        MutableLiveData<Integer> newsData = new MutableLiveData<>();
        likeService.getLikesByArtId(baseLike)
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<OkModel> insertLike(UserLike userLike) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        likeService.createLike(userLike)
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
