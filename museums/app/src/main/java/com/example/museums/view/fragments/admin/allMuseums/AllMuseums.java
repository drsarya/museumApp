package com.example.museums.view.fragments.admin.allMuseums;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Museum;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.createMuseum.DialogMuseumCreated;
import com.example.museums.view.fragments.common.Dialogs.DialogLogOut;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllMuseums extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private QueryAllMuseums queryAllMuseums;
    public ProgressBar progressBar;
    private MuseumsRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private ImageButton imbtn;
    private EditText search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_admin_all_museums, container, false);
        return rootView;
    }

    private void initViews() {
        search = getActivity().findViewById(R.id.admin_all_museums_search_edit_text);
        recyclerView = getActivity().findViewById(R.id.admin_all_museums_recyclerView);
        progressBar = getActivity().findViewById(R.id.admin_all_museums_progress_bar);
        mAdapter = new MuseumsRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        imbtn = getActivity().findViewById(R.id.admin_all_museums_menu_popup);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initViews();
        setListMuseum();
        setListeners();
    }

    List<Museum> newExhibitModels = new ArrayList<>();

    private void setListeners() {
        imbtn.setOnClickListener(this::showPopup);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerView.setVisibility(View.VISIBLE);
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void showPopup(View view) {
        Context wrapper = new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.menuStyle);
        PopupMenu popup = new PopupMenu(wrapper, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_admin_logout, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_admin_page_logout:
                DialogLogOut myFragment = new DialogLogOut();
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                myFragment.show(ft, "dialog");
                return true;

            default:
                return false;
        }
    }

    private void setListMuseum() {
        queryAllMuseums = new QueryAllMuseums(this);
        queryAllMuseums.getQuery();
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void filter(String text) {
        List<Museum> temp = new ArrayList();
        for (Museum d : newExhibitModels) {
            if (containsString(d.nameMuseum, text) || containsString(d.address, text)) {
                temp.add(d);
            }
        }
        mAdapter.submitList(temp);
    }

    public void refreshAllList(List<Museum> museums) {

        newExhibitModels = museums;
        mAdapter.submitList(museums);
    }
}
