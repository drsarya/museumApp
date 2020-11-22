package com.example.museums.view.services.Listeners.clickListeners;

import android.view.View;
import android.widget.TextView;


public class ClickListenerHideDescription implements View.OnClickListener {
    public ClickListenerHideDescription(TextView textView ) {
        this.textView = textView;

    }

    private TextView textView;



    @Override
    public void onClick(View v) {
        if ( textView.getVisibility() ==View.VISIBLE) {

            textView.setVisibility(View.GONE);
        } else {

            textView.setVisibility(View.VISIBLE);
        }
    }
}
