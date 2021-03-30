package com.example.museums.API.services.repository;

import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;
import com.example.museums.API.presenter.BasePresenter;


public interface LikeRepos {
    void getLikesByArtId( BaseLike baseLike, BasePresenter.Presenter viewContract);

    void getLikeByUser( UserLike userLike, BasePresenter.Presenter viewContract);

    void deleteLikeByUser(  UserLike userLike, BasePresenter.Presenter viewContract);

    void createLike( UserLike userLike, BasePresenter.Presenter viewContract);

    void getLikedExhibitsByUser( Integer idUser, BasePresenter.Presenter viewContract);

    void getLikedExhibitionsByUser(  Integer idUser, BasePresenter.Presenter viewContract);

}
