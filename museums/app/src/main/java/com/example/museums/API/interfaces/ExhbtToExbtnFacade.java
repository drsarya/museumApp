package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;

import java.util.List;

public interface ExhbtToExbtnFacade {

    void getExhibitsByExhdtnId(String ixhbtnId);

     void insertExhbToExbtn( ExhibitToExhbtn  exhbtns);

}

