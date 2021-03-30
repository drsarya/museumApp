//package com.example.museums.view.services.recyclerViews;
//
//import android.annotation.SuppressLint;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
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
//import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHolderMuseumAdminEditPage;
//
//import java.util.List;
//
//public class MuseumsRecyclerViewAdapter extends RecyclerView.Adapter<MuseumsRecyclerViewAdapter.MuseumsViewHolder> {
//    public static class MuseumsViewHolder extends RecyclerView.ViewHolder {
//        public TextView textView;
//
//        public MuseumsViewHolder(View view) {
//            super(view);
//            textView = view.findViewById(R.id.element_of_list_museum);
//        }
//    }
//
//    private AllMuseums allMuseums;
//
//    public MuseumsRecyclerViewAdapter(AllMuseums allMuseums) {
//        this.allMuseums = allMuseums;
//    }
//
//    @NonNull
//    @Override
//    public MuseumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.element_of_list_museum, parent, false);
//
//        MuseumsRecyclerViewAdapter.MuseumsViewHolder vh = new MuseumsRecyclerViewAdapter.MuseumsViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MuseumsViewHolder holder, int position) {
//
//        final Museum purchaseList = differ.getCurrentList().get(position);
//        holder.itemView.setOnClickListener(new ClickListenerHolderMuseumAdminEditPage(holder, purchaseList, allMuseums));
//        holder.textView.setText(purchaseList.nameMuseum);
//    }
//
//    private AsyncListDiffer<Museum> differ = new AsyncListDiffer<Museum>(this, DIFF_CALLBACK);
//
//    private static final DiffUtil.ItemCallback<Museum> DIFF_CALLBACK = new DiffUtil.ItemCallback<Museum>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Museum oldProduct, @NonNull Museum newProduct) {
//            return oldProduct.id.equals(newProduct.id);
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        @Override
//        public boolean areContentsTheSame(@NonNull Museum oldProduct, @NonNull Museum newProduct) {
//            return oldProduct.equals(newProduct);
//        }
//    };
//
//    public void submitList(List<Museum> products) {
//        differ.submitList(products);
//    }
//
//    @Override
//    public int getItemCount() {
//        return differ.getCurrentList().size();
//    }
//
//
//}
