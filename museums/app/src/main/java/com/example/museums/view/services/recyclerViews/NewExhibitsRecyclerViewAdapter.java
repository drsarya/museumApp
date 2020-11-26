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

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderEditExhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;
import com.example.museums.API.models.Exhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickOnListenerHolderExhbtn;

import java.util.ArrayList;
import java.util.List;

public class NewExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder> {

    private List<NewExhibitModel> mDataset;
    private CreateExhibition createExhibition;
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
           edit =  view.findViewById(R.id.new_exhbt_edit );
        }
    }

    public NewExhibitsRecyclerViewAdapter(List<NewExhibitModel> myDataset, CreateExhibition createExhibition) {
        mDataset = myDataset;
        this.createExhibition = createExhibition;
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

        holder.image.setImageBitmap(mDataset.get(position).photo);

        holder.nameOfExhbr.setText(mDataset.get(position).name);
        holder.edit.setOnClickListener(new ClickListenerHolderEditExhibit(createExhibition, mDataset.get(position), position));
//добавить alert  в литснере
    holder.delete.setOnClickListener(new ClickListenerHolderDeletePosition(this, position, mDataset));
    }

    public void updateAll(List<NewExhibitModel> exhibits) {
        mDataset = new ArrayList<>();
        mDataset.addAll(exhibits);
        notifyItemRemoved(exhibits.size() - 1);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
