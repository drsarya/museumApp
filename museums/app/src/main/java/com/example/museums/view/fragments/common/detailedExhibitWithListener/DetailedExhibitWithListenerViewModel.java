package com.example.museums.view.fragments.common.detailedExhibitWithListener;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.enums.TypeOfArtEnum;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;

public class DetailedExhibitWithListenerViewModel extends ViewModel {

    private DetailedExhibitWithListenerRepository repository = DetailedExhibitWithListenerRepository.getInstance();


    public LiveData<BaseLike> getUserLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBIT, userId);
        return repository.getUserLike(userLike);
    }

    public LiveData<String> getCountOfLikeOnExhibition(Integer artId) {
        BaseLike baseLike = new BaseLike(artId, TypeOfArtEnum.EXHIBIT);
        return repository.getCountOfLikeOnExhibition(baseLike);
    }

    public LiveData<AnswerModel> insertLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBIT, userId);
        return repository.insertLike(userLike);
    }
}
