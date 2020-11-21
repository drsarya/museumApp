package com.example.museums.view.services.recyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.ClickListenerHolderMuseum;

import java.util.List;

public class MuseumsRecyclerViewAdapter extends RecyclerView.Adapter<MuseumsRecyclerViewAdapter.MuseumsViewHolder> {
    public static class MuseumsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;



        public MuseumsViewHolder(View view) {
            super(view);

             textView = view.findViewById(R.id.element_of_list_museum);

        }
    }
    private List<Museum> mDataset;

    public MuseumsRecyclerViewAdapter(List<Museum> myDataset) {
        this.mDataset = myDataset;

    }

    @NonNull
    @Override
    public MuseumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_museum, parent, false);

        MuseumsRecyclerViewAdapter.MuseumsViewHolder vh = new MuseumsRecyclerViewAdapter.MuseumsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumsViewHolder holder, int position) {
       holder.itemView.setOnClickListener(new ClickListenerHolderMuseum(holder));

        holder.textView.setText("Третьяковская галерея" + position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
