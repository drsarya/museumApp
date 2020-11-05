package com.example.museums.services.Listeners;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.museums.R;

public class ClickListenerChangeColorLike implements View.OnClickListener {
    public ClickListenerChangeColorLike(Boolean state, ImageView like, FragmentActivity activity, boolean trof) {
        this.like = like;
        this.trof = trof;
        this.state = state;
        this.activity = activity;
    }
    private FragmentActivity activity;
    private Boolean trof;
    private Boolean state;
    private ImageView like;

    @Override
    public void onClick(View v) {
        if(trof){
         if (state) {
            like.setColorFilter(activity.getResources().getColor(R.color.lightBeige));
            state = false;
        } else {
            like.setColorFilter(activity.getResources().getColor(R.color.pink));
            state = true;
        }}else{
            if (state) {
                like.setColorFilter(activity.getResources().getColor(R.color.brown));
                state = false;
            } else {
                like.setColorFilter(activity.getResources().getColor(R.color.pink));
                state = true;
            }
        }
    }

}
