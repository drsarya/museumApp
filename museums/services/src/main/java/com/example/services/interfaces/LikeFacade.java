package com.example.services.interfaces;

public interface LikeFacade {

    String getLikesByExhId(String exhbtnId);

    int deleteLikesByExhbtId(String iduser, String idExhb);

    long insertLike(String iduser, String idExhb);

}
