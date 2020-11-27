package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    List<Exhibit> getAllExhibits();

    void insertExhibits(List<Exhibit> exhibits);

}
