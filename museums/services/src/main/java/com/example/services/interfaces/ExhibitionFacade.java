package com.example.services.interfaces;

import com.example.services.models.Exhibition;

import java.util.List;

public interface ExhibitionFacade {
    List<Exhibition> getAllExhibitions();

    List<Exhibition> getExhbtnByMuseumId(String id);

    long insertExhbtn(Exhibition exhibition);

}
