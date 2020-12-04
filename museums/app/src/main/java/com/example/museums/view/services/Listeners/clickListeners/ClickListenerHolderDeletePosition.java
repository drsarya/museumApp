package com.example.museums.view.services.Listeners.clickListeners;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.view.services.Handlers.HandlerTimerCountDown;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.EditExhibitionRecyclerAdapter;

public class ClickListenerHolderDeletePosition implements View.OnClickListener {
    private RecyclerView.Adapter adapter;
    private int position;
    private IDeletePosition iDeletePosition;
    private Context context;
    private View view;
    private Integer id;

    public ClickListenerHolderDeletePosition(RecyclerView.Adapter adapter, IDeletePosition iDeletePosition, Context context,
                                             View view, int position, Integer id) {
        this.adapter = adapter;
        this.context = context;
        this.iDeletePosition = iDeletePosition;
        this.position = position;
        this.view = view;
        this.id = id;

    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        String message = "Вы точно хотите удалить данный экспонат";
        if (adapter.getClass().toString().equals(EditExhibitionRecyclerAdapter.class.toString())) {
            message = "Вы точно хотите удалить данную выставку? " +
                    "При удалении все экпонаты из этой выставки будут удалены";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AboutDialog);
        builder.setTitle("Удалить?")

                .setMessage(message)
                .setPositiveButton("да", (dialog, id) -> {
                    sendMessageToCountDownTimer();
                    updateRecyclerView();
                }).setNegativeButton("нет", (dialog, id) -> dialog.cancel()).show();
    }

    private void sendMessageToCountDownTimer() {
        HandlerTimerCountDown mHandler = new HandlerTimerCountDown(view);
        Message message = mHandler.obtainMessage();
        message.arg2 = -1;
        message.sendToTarget();
    }

    private void updateRecyclerView() {
        System.out.println(id);
        iDeletePosition.deletePosition(position, id);
    }
}