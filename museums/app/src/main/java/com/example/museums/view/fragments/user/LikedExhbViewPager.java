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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;

import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.museums.R;
import com.example.museums.view.fragments.common.dialogs.DialogLogOut;
import com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword.DialogUpdatePassword;
import com.example.museums.view.services.recyclerViews.LikedExhibViewPagerAdapter;

import static com.example.museums.view.ConstantKeys.ID_USER_KEY;

public class LikedExhbViewPager extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private ViewPager mPager;
    private ImageButton imbtn;
    private PagerAdapter pagerAdapter;
    private Integer userId;

    public LikedExhbViewPager() {
    }

    public static LikedExhbViewPager newInstance(Integer userId) {
        final LikedExhbViewPager myFragment = new LikedExhbViewPager();
        final Bundle args = new Bundle(1);
        args.putInt(ID_USER_KEY, userId);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            userId = getArguments().getInt(ID_USER_KEY);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        getArgumentsFromBundle();
        View rootView =
                inflater.inflate(R.layout.fragment_home_page_user, container, false);
        initViews(rootView);
        return rootView;
    }

    public void initViews(View rootView) {
        imbtn = rootView.findViewById(R.id.home_page_user_menu_popup);
        mPager = rootView.findViewById(R.id.liked);
        imbtn.setOnClickListener(this::showPopup);
        pagerAdapter = new LikedExhibViewPagerAdapter(getChildFragmentManager(), userId);
        mPager.setAdapter(pagerAdapter);
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
                DialogUpdatePassword dialogUpdatePassword = new DialogUpdatePassword().newInstance(userId);
                final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                dialogUpdatePassword.show(ft1, "dialog2");
                return true;
            default:
                return false;
        }
    }
}
