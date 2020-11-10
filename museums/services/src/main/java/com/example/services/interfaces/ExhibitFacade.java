package com.example.services.interfaces;

import com.example.services.models.Exhibit;

import java.util.List;

public interface ExhibitFacade {

    List<Exhibit> getAllExhibits();

    long[] insertExhbt(List<Exhibit> exhibits);

}
