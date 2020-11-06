package com.example.museums.services.Listeners;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.museums.R;

public class ClickListenerChangeColorLike implements View.OnClickListener {
    public ClickListenerChangeColorLike(Boolean state, ImageButton like, FragmentActivity activity  ) {
        this.like = like;
        this.state = state;
        this.activity = activity;
     }
    private FragmentActivity activity;

    private Boolean state;
    private ImageButton like;

    @Override
    public void onClick(View v) {

            if (state) {
                like.setColorFilter(activity.getResources().getColor(R.color.brown));
                state = false;
            } else {
                like.setColorFilter(activity.getResources().getColor(R.color.pink));
                state = true;
            }
        }


}
