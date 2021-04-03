package com.example.museums.view.fragments.common.detailedExhibitWithoutListeners;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.enums.TypeOfArtEnum;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;

public class DetailedExhibitWithoutListenerViewModel extends ViewModel {

    private DetailedExhibitWithoutListenerRepository repository = DetailedExhibitWithoutListenerRepository.getInstance();


    public LiveData<BaseLike> getUserLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBIT, userId);
        return repository.getUserLike(userLike);
    }

    public LiveData<Integer> getCountOfLikeOnExhibition(Integer artId) {
        BaseLike baseLike = new BaseLike(artId, TypeOfArtEnum.EXHIBIT);
        return repository.getCountOfLikeOnExhibition(baseLike);
    }

    public LiveData<AnswerModel> insertLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBIT, userId);
        return repository.insertLike(userLike);
    }
}
