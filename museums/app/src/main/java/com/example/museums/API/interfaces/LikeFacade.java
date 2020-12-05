package com.example.museums.API.interfaces;

public interface LikeFacade {

    void getLikesByExhId(String exhbtnId, boolean type);
    void getLikeByUserId(Integer userId, String idExh, boolean type);

    void deleteLikesByExhbtId(Integer iduser, String idExhb, boolean type);

    void insertLike(Integer iduser, String idExhb, boolean type);

}
