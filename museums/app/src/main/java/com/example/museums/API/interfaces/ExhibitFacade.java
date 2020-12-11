package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    void getAllExhibits();

    void getExhibitsByMuseumLogin(String login);

    void deleteExhibits(int idExhibition);

     void insertExhibit( Exhibit  exhibit);

    void deleteExhibit(int id);

    void updateExhibit(Exhibit exhibit);
    void getExhibitsByMuseumId(Integer id);
 }
