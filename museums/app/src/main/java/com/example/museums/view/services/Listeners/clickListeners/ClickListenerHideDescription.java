//package com.example.museums.view.services.Listeners.clickListeners;
//
//import android.app.Activity;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.TextView;
//
//
//public class ClickListenerHideDescription implements View.OnClickListener {
//    public ClickListenerHideDescription(View textView) {
//        this.textView = textView;
//    }
//
//    private View textView;
//
//
//    @Override
//    public void onClick(View v) {
//        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        if (textView.getVisibility() == View.VISIBLE) {
//            textView.setVisibility(View.GONE);
//        } else {
//            textView.setVisibility(View.VISIBLE);
//        }
//    }
//}
