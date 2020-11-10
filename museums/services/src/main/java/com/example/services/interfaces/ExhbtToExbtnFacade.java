package com.example.services.interfaces;

import com.example.services.models.Exhibit;
import com.example.services.models.ExhibitToExhbtn;

import java.util.List;

public interface ExhbtToExbtnFacade {

    List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId);

    long[] insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns);

}

