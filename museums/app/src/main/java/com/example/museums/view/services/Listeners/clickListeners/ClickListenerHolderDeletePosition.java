package com.example.museums.view.services.Listeners.clickListeners;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.services.Handlers.HandlerTimerCountDown;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.util.List;

public class ClickListenerHolderDeletePosition implements View.OnClickListener {
    private RecyclerView.Adapter adapter;
    private int position;
    private IDeletePosition iDeletePosition;
    private Context context;
    private View view;
    private int id;

    public ClickListenerHolderDeletePosition(RecyclerView.Adapter adapter, IDeletePosition iDeletePosition, Context context,
                                             View view, int position, int id) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AboutDialog);
        builder.setTitle("Удалить?")

                .setMessage("Вы точно хотите удалить данный экспонат")
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
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
        //Вызвать метод класса родителя
        iDeletePosition.deletePosition(position, id);
        //  newExhibitsModelList.remove(position);
    }
}