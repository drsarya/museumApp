package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibition;

import java.util.List;

public interface ExhibitionFacade {
   void  getAllExhibitions();

    void getMuseumByLogin(String login);

    void insertExhbtn(Exhibition exhibition);

    void updateExhibition(Exhibition exhibition);

    void getExhibitionByMuseumId(int id);

    void deleteExhibition(int id);
}
