package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.R;
import com.example.museums.view.fragments.user.exhibits.Exhibits;

import java.util.List;

public class HorizontalMuseumsRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalMuseumsRecyclerViewAdapter.TagsViewHolder> {
    public static class TagsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public TagsViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.elemt_of_list_tags);
        }
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_tags, parent, false);
        HorizontalMuseumsRecyclerViewAdapter.TagsViewHolder vh = new HorizontalMuseumsRecyclerViewAdapter.TagsViewHolder(v);
        return vh;
    }


    private Exhibits exhibits;

    public HorizontalMuseumsRecyclerViewAdapter(Exhibits exhibits) {
        this.exhibits = exhibits;
    }


    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {
        ExistingMuseum purchaseList = differ.getCurrentList().get(position);

        holder.textView.setText(purchaseList.getName());
        holder.itemView.setOnClickListener(v -> {
            exhibits.clickHorizontalViewHolder(purchaseList.getId());
        });
    }

    private AsyncListDiffer<ExistingMuseum> differ = new AsyncListDiffer<ExistingMuseum>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<ExistingMuseum> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExistingMuseum>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExistingMuseum oldProduct, @NonNull ExistingMuseum newProduct) {
            return oldProduct.getId().equals(newProduct.getId());
        }
        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull ExistingMuseum oldProduct, @NonNull ExistingMuseum newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    public void submitList(List<ExistingMuseum> products) {
        differ.submitList(products);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
