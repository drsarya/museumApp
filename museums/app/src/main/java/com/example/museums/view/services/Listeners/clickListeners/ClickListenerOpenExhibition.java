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
//import com.example.museums.view.fragments.common.DetailedExhbtn;
//import com.example.museums.view.services.MethodsWithFragment;
//import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
//
//public class ClickListenerOpenExhibition implements View.OnClickListener {
//    private View view;
//    private ExhibitionWithMuseumName exhibitionWithMuseumName;
//    private MethodsWithFragment mth = new MethodsWithFragment();
//    private CountDownTimer ctimte = null;
//
//    public ClickListenerOpenExhibition(View view, ExhibitionWithMuseumName exhibitionWithMuseumName) {
//        this.view = view;
//        this.exhibitionWithMuseumName = exhibitionWithMuseumName;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onClick(View v) {
//        Fragment myFragment;
//        if (view.getVisibility() == View.VISIBLE) {
//            if (exhibitionWithMuseumName.firstDate == null) {
//                myFragment = new DetailedExhbtn().newInstance(exhibitionWithMuseumName.id, Integer.parseInt(exhibitionWithMuseumName.idMuseum), -1, exhibitionWithMuseumName.image, exhibitionWithMuseumName.name,
//                        "", exhibitionWithMuseumName.description);
//            } else {
//                myFragment = new DetailedExhbtn().newInstance(exhibitionWithMuseumName.id, Integer.parseInt(exhibitionWithMuseumName.idMuseum), -1, exhibitionWithMuseumName.image, exhibitionWithMuseumName.name,
//                        exhibitionWithMuseumName.firstDate + " - " + exhibitionWithMuseumName.lastDate, exhibitionWithMuseumName.description);
//            }
//            MuseumTab activity = (MuseumTab) v.getContext();
//            mth.replaceFragment(myFragment, activity);
//        } else {
//            ctimte = new CountDownTimerHideInfo(3000, 3000, view);
//            ctimte.start();
//        }
//    }
//}
