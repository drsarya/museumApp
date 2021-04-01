package com.example.museums.view.fragments.admin.createMuseum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseumsViewModel;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class CreateMuseum extends Fragment {


    private Button regMuseumBtn;
    private EditText logEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private TextFieldBoxes logTextFieldBoxes;
    private TextFieldBoxes nameTextFieldBoxes;
    private TextFieldBoxes addressTextFieldBoxes;
    public ProgressBar progressBar;
    public CreateMuseumViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_admin_create_museum, container, false);

        return rootView;
    }

    private void initViews() {
        regMuseumBtn = getActivity().findViewById(R.id.admin_create_museum_create_museum_btn);
        logEditText = getActivity().findViewById(R.id.admin_create_museum_login_editText);
        nameEditText = getActivity().findViewById(R.id.admin_create_museum_name_of_museum_editText);
        addressEditText = getActivity().findViewById(R.id.admin_create_museum_address_of_museum_editText);
        logTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_login_ext_field);
        nameTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_name_of_museum_text_field);
        addressTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_address_of_museum_text_field);
        progressBar = getActivity().findViewById(R.id.admin_create_museum_progress_bar);
    }

    private void setListeners() {
        logEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(logTextFieldBoxes));
        regMuseumBtn.setOnClickListener(
                v -> {
                    viewModel = ViewModelProviders.of(this).get(CreateMuseumViewModel.class);
                    viewModel.getIsLoading().observe(this, isLoading -> {
                        if (isLoading) progressBar.setVisibility(View.VISIBLE);
                        else progressBar.setVisibility(View.GONE);
                    });
                    viewModel.getLiveDataUser(logEditText.getText().toString(), nameEditText.getText().toString(), addressEditText.getText().toString())
                            .observe(this, model -> {
                                viewModel.getIsLoading().postValue(false);
                                if (model == null) {
                                    Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
        );
        //кнопка расшарить!!!!!!!!!!!!!!!
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initViews();
        setListeners();
    }


//        DialogMuseumCreated myFragment = new DialogMuseumCreated();
//        Bundle bd = new Bundle();
//        bd.putString(myFragment.CODE_KEY, idCode.toString());
//        bd.putString(myFragment.LOGIN_KEY, curogin);
//        myFragment.setArguments(bd);
//        AppCompatActivity ss = (AppCompatActivity) getContext() ;
//        final FragmentTransaction ft = ss.getSupportFragmentManager().beginTransaction();
//        myFragment.show(ft, "dialog");
// 
//    }


}
