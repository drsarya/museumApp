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

import com.example.museums.API.models.ExhibitWithAuthor;
import com.example.museums.API.models.Exhibition;
import com.example.museums.API.models.ExhibitionWithMuseumName;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.editExhibit.EditExhibit;
import com.example.museums.view.fragments.museum.editExhibition.EditExhibition;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerOpenExhibition;
import com.example.museums.view.services.Listeners.clickListeners.ClickOnListenerHolderExhbtn;

import java.util.ArrayList;
import java.util.List;

public class EditExhibitionRecyclerAdapter extends RecyclerView.Adapter<EditExhibitionRecyclerAdapter.EditExhibitionViewHolder> {
    public static class EditExhibitionViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ImageButton editExhibition, deleteExhibition;
        private TextView nameOfExhibitionTextView, dateOfCreateTextView, nameOfMuseumTextView;
        private LinearLayout optionalPanel;

        public EditExhibitionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_edit_exhibition_image);
            nameOfExhibitionTextView = itemView.findViewById(R.id.detailed_edit_exhibition_name_text_view);
            dateOfCreateTextView = itemView.findViewById(R.id.detailed_edit_exhibition_date_text_view);
            nameOfMuseumTextView = itemView.findViewById(R.id.detailed_edit_exhibition_name_museum);
            optionalPanel = itemView.findViewById(R.id.element_list_edit_exhibition_linear_layout);
            deleteExhibition = itemView.findViewById(R.id.element_list_edit_exhibition_delete_exhibit_image_button);
            editExhibition = itemView.findViewById(R.id.element_list_edit_exhibition_edit_exhibit_image_button);
        }

    }
    private EditExhibition editExhibition;

    public EditExhibitionRecyclerAdapter(EditExhibition editExhibition) {
        this.editExhibition = editExhibition;
    }
    private AsyncListDiffer<ExhibitionWithMuseumName> differ = new AsyncListDiffer<ExhibitionWithMuseumName>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<ExhibitionWithMuseumName> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExhibitionWithMuseumName>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExhibitionWithMuseumName oldProduct, @NonNull ExhibitionWithMuseumName newProduct) {
            return oldProduct.id == newProduct.id;
        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull ExhibitionWithMuseumName oldProduct, @NonNull ExhibitionWithMuseumName newProduct) {
            return oldProduct.equals(newProduct);
        }
    };


    @NonNull
    @Override
    public EditExhibitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_edit_exhibitions, parent, false);

        return new EditExhibitionViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull EditExhibitionViewHolder holder, int position) {
        final ExhibitionWithMuseumName exhibition = differ.getCurrentList().get(position);

        holder.itemView.setOnClickListener(new ClickListenerOpenExhibition(holder.optionalPanel, exhibition));
        holder.deleteExhibition.setOnClickListener(new ClickListenerHolderDeletePosition( this, editExhibition, editExhibition.getContext(),
                holder.optionalPanel, holder.getAdapterPosition(), exhibition.id));

                holder.imageView.setImageBitmap(exhibition.image);
        if (exhibition.firstDate == null) {
            holder.dateOfCreateTextView.setVisibility(View.GONE);
        } else {
            holder.dateOfCreateTextView.setVisibility(View.VISIBLE);
            holder.dateOfCreateTextView.setText(exhibition.firstDate + " - " + exhibition.lastDate);
        }
        holder.nameOfExhibitionTextView.setText(exhibition.name);
        holder.nameOfMuseumTextView.setText(exhibition.nameMuseum);

    }

    public void submitList(List<ExhibitionWithMuseumName> products) {
        differ.submitList(products);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
