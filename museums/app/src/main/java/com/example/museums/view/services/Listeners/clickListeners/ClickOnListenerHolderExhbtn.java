//package com.example.museums.view.services.Listeners.clickListeners;
//
//import android.os.Build;
//import android.view.View;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.view.fragments.common.DetailedExhbtn;
//import com.example.museums.view.services.MethodsWithFragment;
//
//public class ClickOnListenerHolderExhbtn implements View.OnClickListener {
//    private MethodsWithFragment mth = new MethodsWithFragment();
//    private ExhibitionWithMuseumName exhibitionWithMuseumName;
//    private Integer userId;
//
//    public ClickOnListenerHolderExhbtn(ExhibitionWithMuseumName exhibitionWithMuseumName, Integer userId) {
//        this.exhibitionWithMuseumName = exhibitionWithMuseumName;
//        this.userId = userId;
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onClick(View v) {
//        Fragment myFragment;
//        if (exhibitionWithMuseumName.firstDate == null) {
//            myFragment = new DetailedExhbtn().newInstance(exhibitionWithMuseumName.id, Integer.parseInt(exhibitionWithMuseumName.idMuseum), userId, exhibitionWithMuseumName.image, exhibitionWithMuseumName.name,
//                    "", exhibitionWithMuseumName.description);
//        } else {
//            myFragment = new DetailedExhbtn().newInstance(exhibitionWithMuseumName.id, Integer.parseInt(exhibitionWithMuseumName.idMuseum), userId, exhibitionWithMuseumName.image, exhibitionWithMuseumName.name,
//                    exhibitionWithMuseumName.firstDate + " - " + exhibitionWithMuseumName.lastDate, exhibitionWithMuseumName.description);
//        }
//
//        mth.replaceFragment(myFragment, (AppCompatActivity) v.getContext());
//    }
//}
