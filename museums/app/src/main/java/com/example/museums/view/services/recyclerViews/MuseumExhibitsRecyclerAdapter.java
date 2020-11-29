package com.example.museums.view.services.recyclerViews;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitWithAuthor;
import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderEditExhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;
import com.example.museums.view.services.oop.IDeletePosition;

import java.util.ArrayList;
import java.util.List;

public class MuseumExhibitsRecyclerAdapter extends RecyclerView.Adapter<MuseumExhibitsRecyclerAdapter.MuseumExhibitsViewHolder> {

    public static class MuseumExhibitsViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImage;
        private TextView nameTextView;
        private TextView authorTextView;
        private ImageButton deleteExhibit;
        private ImageButton editExhibit;
        private LinearLayout optionalPanel;
        private TextView dataTextView;

        public MuseumExhibitsViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.element_list_museum_exhibits_image_image_view);
            nameTextView = itemView.findViewById(R.id.element_list_museum_exhibits_name_text_view);
            authorTextView = itemView.findViewById(R.id.element_list_museum_exhibits_author_text_view);
            optionalPanel = itemView.findViewById(R.id.element_list_museum_exhibits_linear_layout);
            editExhibit = itemView.findViewById(R.id.element_list_museum_exhibits_edit_exhibit_image_button);
            deleteExhibit = itemView.findViewById(R.id.element_list_museum_exhibits_delete_exhibit_image_button);
            dataTextView = itemView.findViewById(R.id.element_list_museum_exhibits_data_text_view);
        }
    }

    private List<ExhibitWithAuthor> exhibits;
    private MuseumExhibits museumExhibits;

    public MuseumExhibitsRecyclerAdapter(List<ExhibitWithAuthor> exhibits, MuseumExhibits museumExhibits) {
        this.exhibits = exhibits;
        this.museumExhibits = museumExhibits;
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
        holder.editExhibit.setOnClickListener(new ClickListenerHolderEditExhibit(exhibits.get(position), position, museumExhibits));
        holder.itemView.setOnClickListener(new ClickListenerHolderNewExhibit(holder.optionalPanel));
        holder.deleteExhibit.setOnClickListener(new ClickListenerHolderDeletePosition(this, museumExhibits, museumExhibits.getContext(),
                holder.optionalPanel, position, exhibits.get(position).id));

        holder.authorTextView.setText(exhibits.get(position).fullName);
        holder.mainImage.setImageBitmap(exhibits.get(position).photo);
        holder.nameTextView.setText(exhibits.get(position).name);
        holder.dataTextView.setText(exhibits.get(position).dateOfCreate);
    }

    public void updateAll(List<ExhibitWithAuthor> museum) {
        exhibits = new ArrayList<>();
        exhibits.addAll(museum);
        notifyDataSetChanged();
    }
    public void updateAll( ) {

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return exhibits.size();
    }

}
