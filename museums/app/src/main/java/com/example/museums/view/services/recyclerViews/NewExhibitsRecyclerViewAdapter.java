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
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderEditExhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;

import java.util.List;
import java.util.Objects;

public class NewExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder> {

    private EditExhibition editExhibition;

    public static class NewExhibitsViewHolder extends RecyclerView.ViewHolder {
        public TextView nameOfExhbr;
        public LinearLayout optionalPanel;
        public ImageView image;
        public ImageButton delete;
        public ImageButton edit;

        public NewExhibitsViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.new_exhbt_main_image_view);
            nameOfExhbr = view.findViewById(R.id.new_exhbt_name_text_view);
            optionalPanel = view.findViewById(R.id.new_exhbt_optional_panel_linear_layout);
            delete = view.findViewById(R.id.new_exhbt_delete);
            edit = view.findViewById(R.id.new_exhbt_edit);
        }
    }

    public NewExhibitsRecyclerViewAdapter(EditExhibition editExhibition) {

        this.editExhibition = editExhibition;
    }

    private AsyncListDiffer<ExistingExhibit> differ = new AsyncListDiffer<ExistingExhibit>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<ExistingExhibit> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExistingExhibit>() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areItemsTheSame(@NonNull ExistingExhibit oldProduct, @NonNull ExistingExhibit newProduct) {
            return Objects.equals(oldProduct.getId(), newProduct.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull ExistingExhibit oldProduct, @NonNull ExistingExhibit newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    @NonNull
    @Override
    public NewExhibitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_new_exhbt, parent, false);
        NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder vh = new NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewExhibitsViewHolder holder, int position) {
        final ExistingExhibit exhibit = differ.getCurrentList().get(position);

        holder.itemView.setOnClickListener(new ClickListenerHolderNewExhibit(holder.optionalPanel, exhibit, -1));
        Glide
                .with(holder.image.getContext())
                .load(exhibit.getImageUrl())
                .into(holder.image);
        holder.nameOfExhbr.setText(exhibit.getName());
        holder.edit.setOnClickListener(new ClickListenerHolderEditExhibit(editExhibition, exhibit, position));
        holder.delete.setOnClickListener(new ClickListenerHolderDeletePosition(this, editExhibition, editExhibition.getContext(), holder.optionalPanel, holder.getAdapterPosition(), exhibit.getId()));
    }


    public void submitList(List<ExistingExhibit> products) {
        differ.submitList(products);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
