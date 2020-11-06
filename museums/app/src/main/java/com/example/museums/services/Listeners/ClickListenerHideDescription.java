package com.example.museums.services.Listeners;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class ClickListenerHideDescription  implements View.OnClickListener {
    public ClickListenerHideDescription(TextView textView, Boolean state  ) {
        this.textView = textView;
        this.state = state;
    }
    private TextView textView;

    private Boolean state;
    @Override
    public void onClick(View v) {
        if (state) {
            state = false;
            textView.setVisibility(View.GONE);
        } else {
            state = true;
            textView.setVisibility(View.VISIBLE);
        }
    }
}
