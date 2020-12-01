package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibition;

import java.util.List;

public interface ExhibitionFacade {
    List<Exhibition> getAllExhibitions();

    void getMuseumByLogin(String login);

    void insertExhbtn(Exhibition exhibition);

    void getExhibitionByMuseumId(int id);
}
