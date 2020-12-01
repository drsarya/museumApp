package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    List<Exhibit> getAllExhibits();

    void getExhibitsByMuseumLogin(String login);

    void deleteExhibits(int idExhibition);

    void insertExhibits(List<Exhibit> exhibits);

    void deleteExhibit(int id);

    void updateExhibit(Exhibit exhibit);
}
