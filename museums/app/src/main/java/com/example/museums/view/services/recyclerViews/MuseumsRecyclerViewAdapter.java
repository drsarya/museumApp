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

import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerMuseumAdminEditPage;

import java.util.List;

public class MuseumsRecyclerViewAdapter extends RecyclerView.Adapter<MuseumsRecyclerViewAdapter.MuseumsViewHolder> {
    public static class MuseumsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public MuseumsViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.element_of_list_museum_name_museum_text_view);
            imageView = view.findViewById(R.id.element_of_list_museum_state_image_view);
        }
    }

    private AllMuseums allMuseums;

    public MuseumsRecyclerViewAdapter(AllMuseums allMuseums) {
        this.allMuseums = allMuseums;
    }

    @NonNull
    @Override
    public MuseumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_museum, parent, false);
        return new MuseumsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumsViewHolder holder, int position) {

        final ExistingMuseum purchaseList = differ.getCurrentList().get(position);
        switch (purchaseList.getState()) {
            case ACTIVE:
                holder.imageView.setImageResource(R.drawable.circle_active);
                break;
            case BLOCKED:
                holder.imageView.setImageResource(R.drawable.circle_blocked);
                break;
            case NOT_ACTIVE:
                holder.imageView.setImageResource(R.drawable.circle_not_active);
                break;
        }
        holder.itemView.setOnClickListener(new ClickListenerMuseumAdminEditPage(  purchaseList, allMuseums));
        holder.textView.setText(purchaseList.getName());
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
