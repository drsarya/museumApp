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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibition;
import com.example.museums.view.fragments.museum.museumExhibitions.MuseumExhibitions;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerOpenExhibition;
import com.example.museums.view.services.MethodsWithFragment;

import java.util.List;

public class EditExhibitionRecyclerAdapter extends RecyclerView.Adapter<EditExhibitionRecyclerAdapter.EditExhibitionViewHolder> {
    public static class EditExhibitionViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ImageButton editExhibition, deleteExhibition;
        private TextView nameOfExhibitionTextView, dateOfCreateTextView, nameOfMuseumTextView;
        private LinearLayout optionalPanel;

        public EditExhibitionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_museum_exhibition_image);
            nameOfExhibitionTextView = itemView.findViewById(R.id.element_list_museum_exhibition_name_text_view);
            dateOfCreateTextView = itemView.findViewById(R.id.detailed_museum_exhibition_date_text_view);
            nameOfMuseumTextView = itemView.findViewById(R.id.detailed_museum_exhibition_name_museum);
            optionalPanel = itemView.findViewById(R.id.element_list_museum_exhibition_linear_layout);
            deleteExhibition = itemView.findViewById(R.id.element_list_museum_exhibitions_delete_exhibit_image_button);
            editExhibition = itemView.findViewById(R.id.element_list_museum_exhibition_edit_exhibit_image_button);
        }
    }

    private MuseumExhibitions museumExhibitions;
    private MethodsWithFragment mt = new MethodsWithFragment();

    public EditExhibitionRecyclerAdapter(MuseumExhibitions museumExhibitions) {
        this.museumExhibitions = museumExhibitions;
    }

    private AsyncListDiffer<ExistingExhibition> differ = new AsyncListDiffer<ExistingExhibition>(this, DIFF_CALLBACK);
    private static final DiffUtil.ItemCallback<ExistingExhibition> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExistingExhibition>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExistingExhibition oldProduct, @NonNull ExistingExhibition newProduct) {
            return oldProduct.getId().equals(newProduct.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull ExistingExhibition oldProduct, @NonNull ExistingExhibition newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    @NonNull
    @Override
    public EditExhibitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_museum_exhibitions, parent, false);
        return new EditExhibitionViewHolder(v);
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull EditExhibitionViewHolder holder, int position) {
        final ExistingExhibition exhibition = differ.getCurrentList().get(position);
        holder.itemView.setOnClickListener(new ClickListenerOpenExhibition(holder.optionalPanel, exhibition));
        holder.deleteExhibition.setOnClickListener(new ClickListenerHolderDeletePosition(this, museumExhibitions, museumExhibitions.getContext(),
                holder.optionalPanel, holder.getAdapterPosition(), exhibition.getId()));

        Glide
                .with(holder.imageView.getContext())
                .load(exhibition.getImageUrl())
                .into(holder.imageView);
        if (exhibition.getFirstDate().isEmpty()) {
            holder.dateOfCreateTextView.setVisibility(View.GONE);
        } else {
            holder.dateOfCreateTextView.setVisibility(View.VISIBLE);
            holder.dateOfCreateTextView.setText(exhibition.getFirstDate() + " - " + exhibition.getLastDate());
        }
        EditExhibition c = new EditExhibition().newInstance(exhibition.getId(), exhibition.getMuseum().getId(), exhibition.getImageUrl(), exhibition.getName(), exhibition.getFirstDate(), exhibition.getLastDate(), exhibition.getDescription());
        c.setTargetFragment(museumExhibitions, 0);
        holder.editExhibition.setOnClickListener(v -> mt.replaceFragment(c, (AppCompatActivity) museumExhibitions.getContext()));
        holder.nameOfExhibitionTextView.setText(exhibition.getName());
        holder.nameOfMuseumTextView.setText(exhibition.getMuseum().getName());
    }

    public void submitList(List<ExistingExhibition> products) {
        differ.submitList(products);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
