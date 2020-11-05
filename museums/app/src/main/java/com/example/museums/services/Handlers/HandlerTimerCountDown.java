package com.example.museums.services.Handlers;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;

import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;

public class HandlerTimerCountDown extends Handler {

    public HandlerTimerCountDown(ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder) {
        this.holder = holder;
    }

    private ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder;

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == 20) {
            holder.textView.setVisibility(View.VISIBLE);
        } else if (msg.arg1 == 50) {
            TranslateAnimation animate = new TranslateAnimation(0, 0, 0, holder.textView.getHeight());
            System.out.println(holder.textView.getHeight());
            animate.setDuration(500);
            holder.textView.startAnimation(animate);
            holder.textView.setVisibility(View.GONE);
        }
    }
}
