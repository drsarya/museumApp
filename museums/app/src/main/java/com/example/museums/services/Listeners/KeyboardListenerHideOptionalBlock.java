package com.example.museums.services.Listeners;

import android.view.View;
import android.widget.RelativeLayout;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class KeyboardListenerHideOptionalBlock implements KeyboardVisibilityEventListener {
    private RelativeLayout relativeLayoutMuseumReg;

    public KeyboardListenerHideOptionalBlock(RelativeLayout relativeLayoutMuseumReg) {
        this.relativeLayoutMuseumReg = relativeLayoutMuseumReg;
    }

    @Override
    public void onVisibilityChanged(boolean isOpen) {
        if (isOpen) {
            relativeLayoutMuseumReg.setVisibility(View.GONE);
        } else {
            relativeLayoutMuseumReg.setVisibility(View.VISIBLE);
        }
    }
}
