//package com.example.museums.view.services.Listeners.clickListeners;
//
//import android.os.Build;
//import android.os.CountDownTimer;
//import android.view.View;
//
//import androidx.annotation.RequiresApi;
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.view.activities.tabs.MuseumTab;
//import com.example.museums.view.fragments.common.DetailedExhibitWithListeners;
//import com.example.museums.view.services.MethodsWithFragment;
//import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
//
//public class ClickListenerHolderNewExhibit implements View.OnClickListener {
//    public ClickListenerHolderNewExhibit(View view, NewExhibitModel newExhibitModel, Integer userId) {
//        this.view = view;
//        this.userId = userId;
//        this.model = newExhibitModel;
//    }
//
//    private Integer userId;
//    private CountDownTimer ctimte = null;
//    private View view;
//    private NewExhibitModel model;
//    private MethodsWithFragment mth = new MethodsWithFragment();
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onClick(View v) {
//        Fragment myFragment;
//        if (view.getVisibility() == View.VISIBLE) {
//            myFragment = new DetailedExhibitWithListeners().newInstance(model.exhibitId, userId, model.photo,
//                    model.name, model.author, model.dateOfCreate, model.description);
//            MuseumTab activity = (MuseumTab) v.getContext();
//            mth.replaceFragment(myFragment, activity);
//        } else {
//            ctimte = new CountDownTimerHideInfo(3000, 3000, view);
//            ctimte.start();
//        }
//    }
//}