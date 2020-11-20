package com.example.museums.API.interfaces;

import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;

import java.util.List;

public interface ExhbtToExbtnFacade {

    List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId);

    long[] insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns);

}

