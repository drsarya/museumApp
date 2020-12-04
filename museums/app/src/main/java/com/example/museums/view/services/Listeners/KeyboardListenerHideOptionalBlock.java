package com.example.museums.view.services.Listeners;

import android.view.View;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class KeyboardListenerHideOptionalBlock implements KeyboardVisibilityEventListener {
    private View view;

    public KeyboardListenerHideOptionalBlock(View view) {
        this.view = view;
    }

    @Override
    public void onVisibilityChanged(boolean isOpen) {
        if (isOpen) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
