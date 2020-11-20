package com.example.museums.view.services.recyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;

import java.util.List;

public class TagsRecyclerViewAdapter extends RecyclerView.Adapter<TagsRecyclerViewAdapter.TagsViewHolder> {
    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_tags, parent, false);

        TagsRecyclerViewAdapter.TagsViewHolder vh = new TagsRecyclerViewAdapter.TagsViewHolder(v);
        return vh;
    }

    private List<String> mDataset;

    public TagsRecyclerViewAdapter(List<String> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {

        holder.textView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class TagsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;


        public TagsViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.elemt_of_list_tags);

        }
    }

}
