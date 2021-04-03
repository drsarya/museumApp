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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.R;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseumViewModel;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;
import com.example.museums.view.fragments.common.dialogs.DialogLogOut;
import com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword.DialogUpdatePassword;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllMuseums extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private static final String ID_KEY_USER = "id key";
    public ProgressBar progressBar;
    private MuseumsRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private ImageButton imbtn;
    private EditText search;
    public AllMuseumsViewModel viewModel;
    private Integer id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_admin_all_museums, container, false);
        return rootView;
    }

    public static AllMuseums getInstance(Integer id) {
        final AllMuseums myFragment = new AllMuseums();
        final Bundle args = new Bundle();
        args.putInt(ID_KEY_USER, id);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void initViews() {
        search = getActivity().findViewById(R.id.admin_all_museums_search_edit_text);
        recyclerView = getActivity().findViewById(R.id.admin_all_museums_recyclerView);
        progressBar = getActivity().findViewById(R.id.admin_all_museums_progress_bar);
        mAdapter = new MuseumsRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        imbtn = getActivity().findViewById(R.id.admin_all_museums_menu_popup);
        viewModel = ViewModelProviders.of(this).get(AllMuseumsViewModel.class);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initViews();
        if (search.getText().toString().isEmpty()) {
            getListMuseums();
        } else {
            filter(search.getText().toString());
        }
        setListeners();
    }

    public void getListMuseums() {

         viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataUser()
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        newExhibitModels = model;
                        mAdapter.submitList(model);
                    }
                });
    }

    List<ExistingMuseum> newExhibitModels = new ArrayList<>();

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
        inflater.inflate(R.menu.menu_logout_change_password, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_page_logout:
                DialogLogOut myFragment = new DialogLogOut();
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                myFragment.show(ft, "dialog");
                return true;
            case R.id.menu_item_change_password:
                DialogUpdatePassword dialogUpdatePassword = new DialogUpdatePassword();
                Bundle bd = new Bundle();
                if (id != null) {
                    bd.putInt(DialogUpdatePassword.ID_KEY, id);
                    dialogUpdatePassword.setArguments(bd);
                }
                final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                dialogUpdatePassword.show(ft1, "dialog2");
                return true;
            default:
                return false;
        }
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void filter(String text) {
        List<ExistingMuseum> temp = new ArrayList();
        for (ExistingMuseum d : newExhibitModels) {
            if (containsString(d.getName(), text) || containsString(d.getAddress(), text)) {
                temp.add(d);
            }
        }
        mAdapter.submitList(temp);
    }


}
