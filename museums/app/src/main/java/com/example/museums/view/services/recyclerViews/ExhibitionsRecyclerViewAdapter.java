//package com.example.museums.view.services.recyclerViews;
//
//import android.annotation.SuppressLint;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.recyclerview.widget.AsyncListDiffer;
//import androidx.recyclerview.widget.DiffUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.museums.R;
//import com.example.museums.view.services.Listeners.clickListeners.ClickOnListenerHolderExhbtn;
//
//import java.util.List;
//
//public class ExhibitionsRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder> {
//
//    public static class ExhibitionsViewHolder extends RecyclerView.ViewHolder {
//        public TextView dateOfExhbtn;
//        public TextView nameOfExhibtn;
//        public TextView nameOfEMuseum;
//        public ImageView image;
//
//        public ExhibitionsViewHolder(View view) {
//            super(view);
//            image = view.findViewById(R.id.detailed_exhbtn_image);
//            dateOfExhbtn = view.findViewById(R.id.detailed_exhbtn_date);
//            nameOfExhibtn = view.findViewById(R.id.detailed_exhbtn_name);
//            nameOfEMuseum = view.findViewById(R.id.detailed_exhbtn_name_museum);
//        }
//    }
//
//    private AsyncListDiffer<ExhibitionWithMuseumName> differ = new AsyncListDiffer<ExhibitionWithMuseumName>(this, DIFF_CALLBACK);
//
//    private static final DiffUtil.ItemCallback<ExhibitionWithMuseumName> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExhibitionWithMuseumName>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull ExhibitionWithMuseumName oldProduct, @NonNull ExhibitionWithMuseumName newProduct) {
//            return oldProduct.id == newProduct.id;
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        @Override
//        public boolean areContentsTheSame(@NonNull ExhibitionWithMuseumName oldProduct, @NonNull ExhibitionWithMuseumName newProduct) {
//            return oldProduct.equals(newProduct);
//        }
//    };
//
//    private Integer userId;
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//
//    }
//
//    public void submitList(List<ExhibitionWithMuseumName> products) {
//        differ.submitList(products);
//    }
//
//    @Override
//    public int getItemCount() {
//        return differ.getCurrentList().size();
//    }
//
//    @NonNull
//    @Override
//    public ExhibitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.element_of_list_exhbtn, parent, false);
//        ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder vh = new ExhibitionsRecyclerViewAdapter.ExhibitionsViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ExhibitionsViewHolder holder, int position) {
//        final ExhibitionWithMuseumName exhibition = differ.getCurrentList().get(position);
//
//        holder.image.setImageBitmap(exhibition.image);
//        if (exhibition.firstDate == null) {
//            holder.dateOfExhbtn.setVisibility(View.GONE);
//        } else {
//            holder.dateOfExhbtn.setVisibility(View.VISIBLE);
//            holder.dateOfExhbtn.setText(exhibition.firstDate + " - " + exhibition.lastDate);
//        }
//        holder.nameOfExhibtn.setText(exhibition.name);
//        holder.nameOfEMuseum.setText(exhibition.nameMuseum);
//        holder.itemView.setOnClickListener(new ClickOnListenerHolderExhbtn(exhibition, userId));
//    }
//
//
//}
