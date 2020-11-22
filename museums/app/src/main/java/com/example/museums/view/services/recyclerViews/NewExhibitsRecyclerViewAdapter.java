package com.example.museums.view.services.recyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;
import com.example.museums.API.models.Exhibit;

import java.util.List;

public class NewExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder> {

    private List<Exhibit> mDataset;

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
//            delete = view.findViewById(R.id.new_exhbt_delete);
//            edit =  view.findViewById(R.id.new_exhbt_edit );
        }
    }

    public NewExhibitsRecyclerViewAdapter(List<Exhibit> myDataset) {
        mDataset = myDataset;
    }

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
        holder.itemView.setOnClickListener(new ClickListenerHolderNewExhibit(holder));
        if (position % 3 == 0) {
            holder.image.setImageResource(R.drawable.image2);
        } else if (position % 2 == 0) {
            holder.image.setImageResource(R.drawable.detailed_exhibition);
        } else {
            holder.image.setImageResource(R.drawable.image3);
        }
        holder.nameOfExhbr.setText("Картина  " + position);

        //   holder.itemView.setOnClickListener(new ClickOnListenerHolderExhbtn());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
