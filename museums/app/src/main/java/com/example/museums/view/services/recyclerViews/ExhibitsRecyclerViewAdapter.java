package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderExhibitis;
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

    private AsyncListDiffer<NewExhibitModel> differ = new AsyncListDiffer<NewExhibitModel>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<NewExhibitModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewExhibitModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewExhibitModel oldProduct, @NonNull NewExhibitModel newProduct) {
            return oldProduct.exhibitId.equals(newProduct.exhibitId);
        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull NewExhibitModel oldProduct, @NonNull NewExhibitModel newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    public void submitList(List<NewExhibitModel> products) {
        differ.submitList(products);
    }


    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
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
        final NewExhibitModel purchaseList = differ.getCurrentList().get(position);

        holder.itemView.setOnClickListener(new ClickListenerHolderExhibitis(holder, purchaseList));
        holder.image.setImageBitmap(purchaseList.photo);
        holder.textView.setText(purchaseList.name);
    }


}
