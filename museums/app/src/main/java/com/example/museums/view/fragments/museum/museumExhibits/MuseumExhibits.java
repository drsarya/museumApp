//package com.example.museums.view.fragments.museum.museumExhibits;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.museums.API.models.exhibit.ExistingExhibit;
//import com.example.museums.R;
//import com.example.museums.view.services.oop.IDeletePosition;
//import com.example.museums.view.services.recyclerViews.MuseumExhibitsRecyclerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MuseumExhibits extends Fragment implements IDeletePosition {
//    private RecyclerView recyclerView;
//    private MuseumExhibitsRecyclerAdapter mAdapter = new MuseumExhibitsRecyclerAdapter(this);
//    public static final String LOGIN_KEY_USER = "login_key";
//    private String login;
//    public ProgressBar progressBar;
//    private QueryDeleteMuseumExhibit queryDeleteMuseumExhibit;
//    private static QueryListMuseumExhibits queryListMuseumExhibits;
//    private EditText searchEditText;
//    private List<ExistingExhibit> newExhibitModels;
//    private static String copySearch = "";
//
//
//    public MuseumExhibits newInstance(String login) {
//        final MuseumExhibits myFragment = new MuseumExhibits();
//        final Bundle args = new Bundle(1);
//        args.putString(LOGIN_KEY_USER, login);
//        myFragment.setArguments(args);
//        return myFragment;
//    }
//
//    private void getArgumentsFromBundle() {
//        if (getArguments() != null) {
//            login = getArguments().getString(LOGIN_KEY_USER);
//        }
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View rootView =
//                inflater.inflate(R.layout.fragment_museum_exhibits, container, false);
//        getArgumentsFromBundle();
//        initView(rootView);
//        setListeners();
//        return rootView;
//    }
//
//    private void setListeners() {
//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                copySearch = "";
//                recyclerView.setVisibility(View.VISIBLE);
//                copySearch += s;
//                filter(s.toString());
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//    }
//
//
//    private boolean containsString(String fullName, String currText) {
//        String newName = fullName.toLowerCase();
//        String newCurrText = currText.toLowerCase();
//        if (newName.contains(newCurrText)) {
//            return true;
//        } else return false;
//    }
//
//    private void filter(String text) {
//        List<ExistingExhibit> temp = new ArrayList();
//        System.out.println(newExhibitModels.size());
//        for (ExistingExhibit d : newExhibitModels) {
//            if (containsString(d.getName(), text) || containsString(d.getAuthor().getFullName(), text)) {
//                temp.add(d);
//            }
//        }
//        mAdapter.submitList(temp);
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//
//    private void initView(View rootView) {
//        searchEditText = rootView.findViewById(R.id.museum_exhibits_search_edit_text);
//        recyclerView = rootView.findViewById(R.id.museum_exhibits_recycler_view);
//        progressBar = rootView.findViewById(R.id.museum_exhibits_recycler_progress_bar);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setHasFixedSize(true);
//        queryListMuseumExhibits = new QueryListMuseumExhibits(this);
//        queryDeleteMuseumExhibit = new QueryDeleteMuseumExhibit(this);
//        if (copySearch.isEmpty()) {
//            queryListMuseumExhibits.getQuery(login);
//        } else {
//            filter(copySearch);
//        }
//    }
//
//
//    public void refreshAllList(List<ExistingExhibit> exhibitModels) {
//        this.newExhibitModels = exhibitModels;
//        mAdapter.submitList(exhibitModels);
//    }
//
//    public void refreshAllList() {
//        queryListMuseumExhibits.getQuery(login);
//    }
//
//    @Override
//    public void deletePosition(int position, Integer id) {
//        queryDeleteMuseumExhibit.getQuery(id);
//    }
//
//
//}
