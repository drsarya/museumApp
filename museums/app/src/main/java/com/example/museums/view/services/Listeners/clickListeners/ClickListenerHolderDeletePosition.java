package com.example.museums.view.services.Listeners.clickListeners;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.services.Handlers.HandlerTimerCountDown;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.util.List;

public class ClickListenerHolderDeletePosition implements View.OnClickListener {
    private NewExhibitsRecyclerViewAdapter adapter;
    private int position;
    private List<NewExhibitModel> newExhibitsModelList;
    private CreateExhibition createExhibition;
    private View view;

    public ClickListenerHolderDeletePosition(NewExhibitsRecyclerViewAdapter adapter, CreateExhibition createExhibition,
                                             View view, int position, List<NewExhibitModel> newExhibitsModelList) {
        this.adapter = adapter;
        this.newExhibitsModelList = newExhibitsModelList;
        this.createExhibition = createExhibition;
        this.position = position;
        this.view = view;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(createExhibition.getContext(), R.style.AboutDialog);
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
        newExhibitsModelList.remove(position);
    }
}