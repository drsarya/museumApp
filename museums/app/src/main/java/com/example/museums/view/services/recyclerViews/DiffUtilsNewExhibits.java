package com.example.museums.view.services.recyclerViews;

import androidx.recyclerview.widget.DiffUtil;

import com.example.museums.API.models.NewExhibitModel;

import java.util.List;


public class DiffUtilsNewExhibits extends DiffUtil.Callback {
    private   List<NewExhibitModel> oldList;
    private   List<NewExhibitModel> newList;

    public DiffUtilsNewExhibits(List<NewExhibitModel> oldList, List<NewExhibitModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
