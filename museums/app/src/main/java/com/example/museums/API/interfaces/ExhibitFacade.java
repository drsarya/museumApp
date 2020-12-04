package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    List<Exhibit> getAllExhibits();

    void getExhibitsByMuseumLogin(String login);

    void deleteExhibits(int idExhibition);

     void insertExhibit( Exhibit  exhibit);

    void deleteExhibit(int id);

    void updateExhibit(Exhibit exhibit);
 }
