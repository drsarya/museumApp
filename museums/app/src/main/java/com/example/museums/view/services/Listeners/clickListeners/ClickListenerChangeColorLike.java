package com.example.museums.view.services.Listeners.clickListeners;

import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;

import com.example.museums.R;
import com.example.museums.view.fragments.common.likes.GetLikesPresenter;

public class ClickListenerChangeColorLike implements View.OnClickListener {
    public ClickListenerChangeColorLike(GetLikesPresenter queryGetLikes) {
        this.presenter = queryGetLikes;
    }

    private GetLikesPresenter presenter;

    @Override
    public void onClick(View v) {
        presenter.insertLike();
    }


}
