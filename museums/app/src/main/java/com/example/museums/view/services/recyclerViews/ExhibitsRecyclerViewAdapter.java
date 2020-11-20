package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.ClickListenerHolderExhibitis;
import com.example.museums.API.models.Exhibit;


import java.util.List;

public class ExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitsRecyclerViewAdapter.ExhibitsViewHolder> {

    public static class ExhibitsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView image;


        public ExhibitsViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.elemt_exhbt_image_view);
            textView = view.findViewById(R.id.elemt_exhbt_text_view);

        }
    }

    private List<Exhibit> mDataset;

    public ExhibitsRecyclerViewAdapter(List<Exhibit> myDataset) {
        this.mDataset = myDataset;

    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public ExhibitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_exhibits, parent, false);

        ExhibitsViewHolder vh = new ExhibitsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitsViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new ClickListenerHolderExhibitis(holder));
        if (position % 3 == 0) {
            holder.image.setImageResource(R.drawable.image2);
        } else if (position % 2 == 0) {
            holder.image.setImageResource(R.drawable.detailed_exhibition);
        } else {
            holder.image.setImageResource(R.drawable.image3);
        }
        holder.textView.setText("add linear layout and then you add" + position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
