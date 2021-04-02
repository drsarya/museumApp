package com.example.museums.view.services.oop;

import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.util.List;

public interface IUpdateList {
    void updateList(List<ExistingExhibit> list);
}
