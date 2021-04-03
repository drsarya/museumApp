package com.example.museums.view.fragments.common.detailedExhibition;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.enums.TypeOfArtEnum;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;

import java.util.List;

public class DetailedExhibitionViewModel extends ViewModel {

    private DetailedExhibitionRepository repository = DetailedExhibitionRepository.getInstance();


    public LiveData<BaseLike> getUserLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBITION, userId);
        return repository.getUserLike(userLike);
    }

    public LiveData<Integer> getCountOfLikeOnExhibition(Integer artId) {
        BaseLike baseLike = new BaseLike(artId, TypeOfArtEnum.EXHIBITION);
        return repository.getCountOfLikeOnExhibition(baseLike);
    }

    public LiveData<AnswerModel> insertLike(Integer artId, Integer userId) {
        UserLike userLike = new UserLike(artId, TypeOfArtEnum.EXHIBITION, userId);
        return repository.insertLike(userLike);
    }

    public LiveData<List<ExistingExhibit>> getExhibitsFromExhibition(Integer id) {
        return repository.getExhibitsFromExhibition(id);
    }


}
