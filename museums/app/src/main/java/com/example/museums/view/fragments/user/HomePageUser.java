package com.example.museums.view.fragments.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.museums.R;

public class HomePageUser extends Fragment implements PopupMenu.OnMenuItemClickListener{
 private ImageButton imbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_home_page_user, container, false);
        imbtn = rootView.findViewById(R.id.home_page_user_menu_popup);
        imbtn.setOnClickListener(v -> showPopup(v));
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);


     }
    public void showPopup(View view) {
        System.out.println("dfdfdfdfdfdfd");
        Context wrapper = new ContextThemeWrapper(getActivity().getApplicationContext() , R.style.menuStyle);
        PopupMenu popup = new PopupMenu(wrapper, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_home_page_user, popup.getMenu());
        popup.setOnMenuItemClickListener( this);
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_page_logout:
                //
                return true;
            case R.id.home_page_change_password:
                //
                return true;
            default:
                return false;
        }
    }
}

