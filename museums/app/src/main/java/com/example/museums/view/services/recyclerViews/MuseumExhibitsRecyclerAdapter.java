package com.example.museums.view.services.recyclerViews;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibit;
import com.example.museums.R;

import java.util.List;

public class MuseumExhibitsRecyclerAdapter extends RecyclerView.Adapter<MuseumExhibitsRecyclerAdapter.MuseumExhibitsViewHolder> {

    public static class MuseumExhibitsViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImage;
        private TextView nameTextView;
        private TextView authorTextView;
        public MuseumExhibitsViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.element_list_museum_exhibits_image_image_view);
            nameTextView = itemView.findViewById(R.id.element_list_museum_exhibits_name_text_view);
            authorTextView = itemView.findViewById(R.id.element_list_museum_exhibits_author_text_view);

        }
    }

    private List<Exhibit> exhibits;

    public MuseumExhibitsRecyclerAdapter(List<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }

    @NonNull
    @Override
    public MuseumExhibitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_museum_exhibits, parent, false);
        MuseumExhibitsViewHolder vh = new MuseumExhibitsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumExhibitsViewHolder holder, int position) {
        //устанавливаем элементы из разметки
    }

    @Override
    public int getItemCount() {
        //устанавливаем размер массива данных
        return exhibits.size();
    }

}
