//package com.example.museums.view.services.Listeners.clickListeners;
//
//import android.os.Build;
//import android.os.CountDownTimer;
//import android.view.View;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.view.fragments.common.DetailedExhibitWithListeners;
//import com.example.museums.view.services.MethodsWithFragment;
//import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
//import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
//
//
//public class ClickListenerHolderExhibits implements View.OnClickListener {
//    public ClickListenerHolderExhibits(ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder, NewExhibitModel model, Integer userId) {
//        this.holder = holder;
//        this.model = model;
//        this.userId = userId;
//    }
//
//    private NewExhibitModel model;
//    private Integer userId;
//    private CountDownTimer ctimte = null;
//    private MethodsWithFragment mth = new MethodsWithFragment();
//    private ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder;
//
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onClick(View v) {
//        if (holder.textView.getVisibility() == View.VISIBLE) {
//            Fragment myFragment = new DetailedExhibitWithListeners().newInstance(model.exhibitId, userId, model.photo,
//                    model.name, model.author, model.dateOfCreate, model.description);
//            ;
//            mth.replaceFragment(myFragment, (AppCompatActivity) v.getContext());
//        } else {
//            ctimte = new CountDownTimerHideInfo(3000, 3000, holder.textView);
//            ctimte.start();
//        }
//    }
//}
