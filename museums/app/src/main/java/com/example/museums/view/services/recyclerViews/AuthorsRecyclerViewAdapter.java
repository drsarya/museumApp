//package com.example.museums.view.services.recyclerViews;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.museums.R;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerSetChosenData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AuthorsRecyclerViewAdapter extends RecyclerView.Adapter<AuthorsRecyclerViewAdapter.AuthorsViewHolder> {
//
//    public static class AuthorsViewHolder extends RecyclerView.ViewHolder {
//        public TextView nameOfExhibtn;
//
//        public AuthorsViewHolder(View view) {
//            super(view);
//            nameOfExhibtn = view.findViewById(R.id.element_of_list_author_text_view);
//        }
//    }
//
//    private List<Author> mDataset;
//    private EditText authorEditText;
//    private RecyclerView recyclerView;
//
//    public AuthorsRecyclerViewAdapter(List<Author> myDataset, EditText authorEditText, RecyclerView recyclerView) {
//        mDataset = myDataset;
//        this.authorEditText = authorEditText;
//        this.recyclerView = recyclerView;
//    }
//
//    public void updateAll(List<Author> authors) {
//        mDataset = new ArrayList<>();
//        mDataset.addAll(authors);
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public AuthorsRecyclerViewAdapter.AuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.element_of_list_authors, parent, false);
//        AuthorsRecyclerViewAdapter.AuthorsViewHolder vh = new AuthorsRecyclerViewAdapter.AuthorsViewHolder(v);
//        return vh;
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull AuthorsRecyclerViewAdapter.AuthorsViewHolder holder, int position) {
//        holder.nameOfExhibtn.setText(mDataset.get(position).fullName);
//        holder.itemView.setOnClickListener(new ClickListenerSetChosenData(mDataset.get(position), authorEditText, recyclerView));
//    }
//
//    public void updateList(List<Author> list) {
//        mDataset = new ArrayList<>();
//        mDataset = list;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataset.size();
//    }
//
//}
