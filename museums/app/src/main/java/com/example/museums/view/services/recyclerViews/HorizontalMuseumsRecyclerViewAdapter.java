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
        Museum purchaseList = differ.getCurrentList().get(position);

        holder.textView.setText(purchaseList.nameMuseum);
        holder.itemView.setOnClickListener(v -> {
            exhibits.clickHorizontalViewHolder(purchaseList.id);
        });
    }

    private AsyncListDiffer<Museum> differ = new AsyncListDiffer<Museum>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<Museum> DIFF_CALLBACK = new DiffUtil.ItemCallback<Museum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Museum oldProduct, @NonNull Museum newProduct) {
            return oldProduct.id.equals(newProduct.id);
        }
        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull Museum oldProduct, @NonNull Museum newProduct) {
            return oldProduct.equals(newProduct);
        }
    };

    public void submitList(List<Museum> products) {
        differ.submitList(products);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
