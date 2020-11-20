package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    List<Exhibit> getAllExhibits();

    long[] insertExhbt(List<Exhibit> exhibits);

}
