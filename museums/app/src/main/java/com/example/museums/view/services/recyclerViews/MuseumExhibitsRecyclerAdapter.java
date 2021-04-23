package com.example.museums.view.services.recyclerViews;


import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderEditExhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;

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
            dataTextView = itemView.findViewById(R.id.element_list_museum_exhibits_date_text_view);
        }
    }

    private MuseumExhibits museumExhibits;

    public MuseumExhibitsRecyclerAdapter(MuseumExhibits museumExhibits) {
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
        final ExistingExhibit purchaseList = differ.getCurrentList().get(position);
        Glide
                .with(holder.mainImage.getContext())
                .load(purchaseList.getImageUrl())
                .into(holder.mainImage);
        holder.editExhibit.setOnClickListener(new ClickListenerHolderEditExhibit(museumExhibits, purchaseList, holder.getAdapterPosition()));
        holder.itemView.setOnClickListener(new ClickListenerHolderNewExhibit(holder.optionalPanel, purchaseList, -1));
        holder.deleteExhibit.setOnClickListener(new ClickListenerHolderDeletePosition(this, museumExhibits, museumExhibits.getContext(),
                holder.optionalPanel, holder.getAdapterPosition(), purchaseList.getId()));
        holder.authorTextView.setText(purchaseList.getAuthor().fullName);
        holder.nameTextView.setText(purchaseList.getName());
        holder.dataTextView.setText(purchaseList.getDateOfCreate());
    }


    private AsyncListDiffer<ExistingExhibit> differ = new AsyncListDiffer<ExistingExhibit>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<ExistingExhibit> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExistingExhibit>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExistingExhibit oldProduct, @NonNull ExistingExhibit newProduct) {
            return oldProduct.getId().equals(newProduct.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull ExistingExhibit oldProduct, @NonNull ExistingExhibit newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    public void submitList(List<ExistingExhibit> products) {
        differ.submitList(products);
    }


    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

}
