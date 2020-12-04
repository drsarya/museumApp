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

import com.example.museums.R;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibtion;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderDeletePosition;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderEditExhibit;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderNewExhibit;

import java.util.List;
import java.util.Objects;

public class NewExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder> {

    private EditExhibtion editExhibtion;

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

    public NewExhibitsRecyclerViewAdapter(EditExhibtion editExhibtion) {

        this.editExhibtion = editExhibtion;
    }

    private AsyncListDiffer<NewExhibitModel> differ = new AsyncListDiffer<NewExhibitModel>(this, DIFF_CALLBACK);

    private static final DiffUtil.ItemCallback<NewExhibitModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewExhibitModel>() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areItemsTheSame(@NonNull NewExhibitModel oldProduct, @NonNull NewExhibitModel newProduct) {
            System.out.println("id old" + oldProduct.exhibitId + "id new" + newProduct.exhibitId);
          //  if (newProduct.exhibitId == null && oldProduct.exhibitId == null) {
                return Objects.equals(oldProduct.exhibitId, newProduct.exhibitId);

//            } else {
//                System.out.println(Objects.equals(oldProduct.exhibitId, newProduct.exhibitId));
//                return Objects.equals(oldProduct.exhibitId, newProduct.exhibitId);
//            }

        }

        @SuppressLint("DiffUtilEquals")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean areContentsTheSame(@NonNull NewExhibitModel oldProduct, @NonNull NewExhibitModel newProduct) {
            System.out.println("id old" + oldProduct.exhibitId + "id new" + newProduct.exhibitId + oldProduct.equals(newProduct));

            return oldProduct.equals(newProduct);
        }
    };

    @NonNull
    @Override
    public NewExhibitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_of_list_new_exhbt, parent, false);
        System.out.println();
        NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder vh = new NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewExhibitsViewHolder holder, int position) {
        final NewExhibitModel exhibition = differ.getCurrentList().get(position);

        holder.itemView.setOnClickListener(new ClickListenerHolderNewExhibit(holder.optionalPanel, exhibition));
        holder.image.setImageBitmap(exhibition.photo);
        holder.nameOfExhbr.setText(exhibition.name);
        holder.edit.setOnClickListener(new ClickListenerHolderEditExhibit(editExhibtion, exhibition, position));
        holder.delete.setOnClickListener(new ClickListenerHolderDeletePosition(this, editExhibtion, editExhibtion.getContext(), holder.optionalPanel, holder.getAdapterPosition(), exhibition.exhibitId));
    }

//    public void updateAll(List<NewExhibitModel> exhibits) {
////        mDataset = new ArrayList<>();
////        mDataset.addAll(exhibits);
//        notifyDataSetChanged();
//    }

    public void submitList(List<NewExhibitModel> products) {
        System.out.println("products" + products.size());
        System.out.println("products efore current" +   differ.getCurrentList().size());
        for (NewExhibitModel a :  products
             ) {
            System.out.println(a.toString());
        }
        differ.submitList(products);

    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }


}
