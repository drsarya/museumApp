package com.example.museums.view.services.recyclerViews;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;

import com.example.museums.API.models.ExhibitWithAuthor;

import java.util.List;

public class MyDiffCallback extends DiffUtil.Callback{

        List<ExhibitWithAuthor> oldPersons;
        List<ExhibitWithAuthor> newPersons;

public MyDiffCallback(List<ExhibitWithAuthor> newPersons, List<ExhibitWithAuthor> oldPersons) {
        this.newPersons = newPersons;
        this.oldPersons = oldPersons;
        }

@Override
public int getOldListSize() {
        return oldPersons.size();
        }

@Override
public int getNewListSize() {
        return newPersons.size();
        }

@Override
public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).id == newPersons.get(newItemPosition).id;
        }

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@Override
public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
        }

@Nullable
@Override
public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
        }
        }
