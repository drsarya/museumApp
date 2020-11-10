package com.example.museums.services.recyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.services.Listeners.ClickOnListenerHolderExhbtn;
import com.example.services.models.Exhibition;

import java.util.List;

public class ExhibitionsRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder> {

    public static class ExhibitionsViewHolder extends RecyclerView.ViewHolder {
        public TextView dateOfExhbtn;
        public TextView nameOfExhibtn;
        public TextView nameOfEMuseum;
        public ImageView image;

        public ExhibitionsViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.detailed_exhbtn_image);
            dateOfExhbtn = view.findViewById(R.id.detailed_exhbtn_date);
            nameOfExhibtn = view.findViewById(R.id.detailed_exhbtn_name);
            nameOfEMuseum = view.findViewById(R.id.detailed_exhbtn_name_museum);
        }
    }

    private List<Exhibition> mDataset;

    public ExhibitionsRecyclerViewAdapter(List<Exhibition> myDataset) {
        mDataset = myDataset;

    }

    @NonNull
    @Override
    public ExhibitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_exhbtn, parent, false);

        ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder vh = new ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitionsViewHolder holder, int position) {
        holder.dateOfExhbtn.setText("30.05-20.08");
        holder.nameOfExhibtn.setText("Выставка" + position);
        holder.nameOfEMuseum.setText("Третьяковская галерея");
        holder.itemView.setOnClickListener(new ClickOnListenerHolderExhbtn());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
