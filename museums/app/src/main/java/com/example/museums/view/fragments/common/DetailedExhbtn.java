//package com.example.museums.view.fragments.common;
//
//import android.annotation.SuppressLint;
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.R;
//import com.example.museums.view.fragments.common.exhibitFromExhibition.ExhibitViewPager;
//import com.example.museums.view.fragments.common.likes.QueryGetLikes;
//import com.example.museums.view.fragments.common.museumInfo.MainInfoMuseum;
//import com.example.museums.view.fragments.museum.exhibition.editExhibition.QueryGetExhibitsFromExhibition;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerChangeColorLike;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerShareExhibit;
//import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;
//import com.example.museums.view.services.MethodsWithFragment;
//import com.example.museums.view.services.oop.ILike;
//import com.example.museums.view.services.oop.IUpdateList;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DetailedExhbtn extends Fragment implements IUpdateList, ILike {
//    private static final String ID_MUSEUM_KEY = "museum_key";
//    private static final String ID_USER_KEY = "user_key";
//    private ImageButton like;
//    private Button museumInfo;
//    private Button allExhibits;
//
//    private Button exhbtnDescriptionBtn;
//    private TextView exhbtnDescriptionTextView;
//    private MethodsWithFragment mth = new MethodsWithFragment();
//    private ScrollView scrollView;
//    private boolean state = true;
//    private TextView nameTextView, dateTextView;
//    private ImageView imageView, share;
//    private ExhibitViewPager exhibitViewPager;
//
//    @SuppressLint("ClickableViewAccessibility")
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        setListeners();
//    }
//
//    private static Bitmap bitmap;
//    public static final String IMAGE_KEY = "image_key";
//    public static final String NAME_KEY = "name_key";
//    public static final String DATE_KEY = "date_key";
//    public static final String DESCRIPTION_KEY = "description_key";
//    public static final String ID_EXHIBIT_KEY = "id_key";
//    private TextView textViewCountLikes;
//
//    public DetailedExhbtn newInstance(int id, int museumId, int userId, Parcelable image, String name, String date, String description) {
//        final DetailedExhbtn myFragment = new DetailedExhbtn();
//        final Bundle args = new Bundle();
//        args.putString(NAME_KEY, name);
//        args.putString(DATE_KEY, date);
//        args.putString(DESCRIPTION_KEY, description);
//        args.putInt(ID_EXHIBIT_KEY, id);
//        args.putInt(ID_USER_KEY, userId);
//        args.putInt(ID_MUSEUM_KEY, museumId);
//        args.putParcelable(IMAGE_KEY, image);
//        myFragment.setArguments(args);
//        return myFragment;
//    }
//
//    private static int idExhibition;
//    private static int museumId;
//    private static Integer userId;
//    private static String name, date, description;
//
//    private void getArgumentsFromBundle() {
//        if (!getArguments().isEmpty()) {
//
//            name = getArguments().getString(NAME_KEY);
//            date = getArguments().getString(DATE_KEY);
//            description = getArguments().getString(DESCRIPTION_KEY);
//            idExhibition = getArguments().getInt(ID_EXHIBIT_KEY);
//            museumId = getArguments().getInt(ID_MUSEUM_KEY);
//            userId = getArguments().getInt(ID_USER_KEY);
//            bitmap = (Bitmap) getArguments().getParcelable(IMAGE_KEY);
//            getArguments().clear();
//        }
//
//    }
//
//    private void setData() {
//        nameTextView.setText(name);
//        System.out.println(name);
//        dateTextView.setText(date);
//        exhbtnDescriptionTextView.setText(description);
//        imageView.setImageBitmap(bitmap);
//    }
//
//    private QueryGetLikes queryGetLikes;
//
//    private void initViews(View rootView) {
//        like = rootView.findViewById(R.id.detailed_exhibition_like_btn);
//        museumInfo = rootView.findViewById(R.id.detailed_exhibition_btn_museum_info);
//        exhbtnDescriptionBtn = rootView.findViewById(R.id.detailed_exhbtn_descriprion_btn);
//        exhbtnDescriptionTextView = rootView.findViewById(R.id.detailed_exhbtn_descriprion_text_view);
//        scrollView = rootView.findViewById(R.id.detailed_exhbtn_scroll_view);
//        allExhibits = rootView.findViewById(R.id.detailed_exhbtn_all_exhbts_btn);
//        nameTextView = rootView.findViewById(R.id.detailed_exhbtn_name_text_view);
//        dateTextView = rootView.findViewById(R.id.detailed_exhbtn_date_text_view);
//        imageView = rootView.findViewById(R.id.detailed_exhbtn_image_image_view);
//        textViewCountLikes = rootView.findViewById(R.id.detailed_exhibition_count_likes_text_view);
//        share = rootView.findViewById(R.id.detailed_exhibition_share_image_view);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View rootView =
//                inflater.inflate(R.layout.fragment_detailed_exhibition, container, false);
//        initViews(rootView);
//        getArgumentsFromBundle();
//        setData();
//        setDataLikes();
//        return rootView;
//    }
//
//    private void setDataLikes() {
//        queryGetLikes = new QueryGetLikes(this, this);
//
//        if (userId != -1) {
//            queryGetLikes.setData(userId, idExhibition, false);
//            queryGetLikes.getCountLikes();
//            queryGetLikes.getState();
//        } else {
//            queryGetLikes.setData(idExhibition, false);
//            queryGetLikes.getCountLikes();
//        }
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//
//    private void setListeners() {
//        if (userId != null && userId != -1) {
//
//            like.setOnClickListener(new ClickListenerChangeColorLike(queryGetLikes));
//        }
//        museumInfo.setOnClickListener(v -> {
//            Fragment myFragment = new MainInfoMuseum().newInstance(museumId);
//            mth.replaceFragment(myFragment, (AppCompatActivity) v.getContext());
//        });
//        exhbtnDescriptionBtn.setOnClickListener(
//                new ClickListenerHideDescription(exhbtnDescriptionTextView)
//        );
//        allExhibits.setOnClickListener(v -> {
//            QueryGetExhibitsFromExhibition queryGetExhibitsFromExhibition = new QueryGetExhibitsFromExhibition(this, this);
//            queryGetExhibitsFromExhibition.getQuery(idExhibition);
//            List<NewExhibitModel> list = new ArrayList<>();
//
//            exhibitViewPager = new ExhibitViewPager(list, userId);
//            mth.replaceFragment(exhibitViewPager, (AppCompatActivity) v.getContext());
//
//        });
//        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));
//        share.setOnClickListener(new ClickListenerShareExhibit(getActivity(), createMessage(), bitmap));
//
//    }
//
//    private String createMessage() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(name + "\n");
//        stringBuilder.append(date + "\n");
//        stringBuilder.append(description + "\n");
//        stringBuilder.append("@App \"Выставочный зал\" by Darya" + "\n");
//        return stringBuilder.toString();
//    }
//
//    private List<NewExhibitModel> newExhibitModels = new ArrayList<>();
//
//    @Override
//    public void updateList(List<NewExhibitModel> list) {
//        if (newExhibitModels.size() == 0) {
//            System.out.println(list.size() + "result");
//            newExhibitModels.addAll(list);
//        }
//        exhibitViewPager.setNewData(newExhibitModels);
//    }
//
//    public void setCountLikesTextView(String str) {
//        textViewCountLikes.setText(str);
//    }
//
//    public void setLiked() {
//        like.setColorFilter(getActivity().getResources().getColor(R.color.pink));
//    }
//
//    public void setDontLiked() {
//        like.setColorFilter(getActivity().getResources().getColor(R.color.brown));
//    }
//}
