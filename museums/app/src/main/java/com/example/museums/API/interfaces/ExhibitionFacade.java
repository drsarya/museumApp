package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibition;

import java.util.List;

public interface ExhibitionFacade {
    List<Exhibition> getAllExhibitions();

    List<Exhibition> getExhbtnByMuseumId(String id);

    long insertExhbtn(Exhibition exhibition);

}
