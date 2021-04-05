package com.example.museums.view.fragments.common.detailedExhibition;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.R;
import com.example.museums.view.fragments.common.museumInfo.MainInfoMuseum;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerShare;
import com.example.museums.view.services.Listeners.onTouchListeners.OnTouchlistenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.oop.IUpdateList;


import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class DetailedExhibition extends Fragment implements IUpdateList {
    private static final String ID_MUSEUM_KEY = "museum_key";
    private static final String ID_USER_KEY = "user_key";
    private ImageButton like;
    private Button museumInfo;
    private Button allExhibits;

    private Button exhbtnDescriptionBtn;
    private TextView exhbtnDescriptionTextView;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ScrollView scrollView;
    private boolean state = true;
    private TextView nameTextView, dateTextView;
    private ImageView imageView, share;
    // private ExhibitViewPager exhibitViewPager;
    private DetailedExhibitionViewModel viewModel;
    private Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListeners();
    }

    private static String imageUrl;
    public static final String IMAGE_KEY = "image_key";
    public static final String NAME_KEY = "name_key";
    public static final String DATE_KEY = "date_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String ID_EXHIBIT_KEY = "id_key";
    private TextView textViewCountLikes;
    private static Integer idExhibition, museumId, userId;

    private static String name, date, description;

    public DetailedExhibition newInstance(int id, int museumId, int userId, String image, String name, String date, String description) {
        final DetailedExhibition myFragment = new DetailedExhibition();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_KEY, date);
        args.putString(DESCRIPTION_KEY, description);
        args.putInt(ID_EXHIBIT_KEY, id);
        args.putInt(ID_USER_KEY, userId);
        args.putInt(ID_MUSEUM_KEY, museumId);
        args.putString(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibition, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        setData();
        setDataLikes();
        return rootView;
    }


    private void getArgumentsFromBundle() {
        if (!getArguments().isEmpty()) {

            name = getArguments().getString(NAME_KEY);
            date = getArguments().getString(DATE_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            idExhibition = getArguments().getInt(ID_EXHIBIT_KEY);
            museumId = getArguments().getInt(ID_MUSEUM_KEY);
            userId = getArguments().getInt(ID_USER_KEY);
            imageUrl = getArguments().getString(IMAGE_KEY);
            getArguments().clear();
        }

    }

    @SuppressLint("CheckResult")
    private void setData() {
        nameTextView.setText(name);
        dateTextView.setText(date);
        exhbtnDescriptionTextView.setText(description);
        Glide.with(getContext())
                .load(imageUrl)
                .into(imageView);
    }


    private void initViews(View rootView) {
        like = rootView.findViewById(R.id.detailed_exhibition_like_btn);
        museumInfo = rootView.findViewById(R.id.detailed_exhibition_btn_museum_info);
        exhbtnDescriptionBtn = rootView.findViewById(R.id.detailed_exhbtn_descriprion_btn);
        exhbtnDescriptionTextView = rootView.findViewById(R.id.detailed_exhbtn_descriprion_text_view);
        scrollView = rootView.findViewById(R.id.detailed_exhbtn_scroll_view);
        allExhibits = rootView.findViewById(R.id.detailed_exhbtn_all_exhbts_btn);
        nameTextView = rootView.findViewById(R.id.detailed_exhbtn_name_text_view);
        dateTextView = rootView.findViewById(R.id.detailed_exhbtn_date_text_view);
        imageView = rootView.findViewById(R.id.detailed_exhbtn_image_image_view);
        textViewCountLikes = rootView.findViewById(R.id.detailed_exhibition_count_likes_text_view);
        share = rootView.findViewById(R.id.detailed_exhibition_share_image_view);
        viewModel = ViewModelProviders.of(this).get(DetailedExhibitionViewModel.class);
    }


    private void setDataLikes() {

        if (userId != -1) {
            getUserLike();
        }
        getCountOfLike();
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)

    private void setListeners() {
        if (userId != null && userId != -1) {
            like.setOnClickListener(v -> insertLike());
        }
        museumInfo.setOnClickListener(v -> {
            Fragment myFragment = new MainInfoMuseum().newInstance(museumId);
            mth.replaceFragment(myFragment, (AppCompatActivity) v.getContext());
        });
        exhbtnDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(exhbtnDescriptionTextView)
        );
        allExhibits.setOnClickListener(v -> {
            getExhibitsByExhibition();
            List<ExistingExhibit> list = new ArrayList<>();
            //exhibitViewPager = new ExhibitViewPager(list, userId);
            // mth.replaceFragment(exhibitViewPager, (AppCompatActivity) v.getContext());

        });
        scrollView.setOnTouchListener(new OnTouchlistenerScrollViewSwipeLeftRightBack(getActivity(), false));


        share.setOnClickListener(new ClickListenerShare(getActivity(), createMessage(), imageUrl));

    }

    private String createMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "\n");
        stringBuilder.append(date + "\n");
        stringBuilder.append(description + "\n");
        stringBuilder.append("@App \"Выставочный зал\" by Darya" + "\n");
        return stringBuilder.toString();
    }

    private List<ExistingExhibit> newExhibitModels = new ArrayList<>();

    @Override
    public void updateList(List<ExistingExhibit> list) {
        if (newExhibitModels.size() == 0) {
            newExhibitModels.addAll(list);
        }
        //  exhibitViewPager.setNewData(newExhibitModels);
    }


    private void insertLike() {
        viewModel.insertLike(idExhibition, userId)
                .observe(this, aBoolean -> getUserLike());
    }

    private void getUserLike() {
        viewModel.getUserLike(idExhibition, userId)
                .observe(this, aBoolean -> {
                    if (aBoolean == null) {
                        like.setColorFilter(getActivity().getResources().getColor(R.color.brown));
                    } else {
                        like.setColorFilter(getActivity().getResources().getColor(R.color.pink));
                    }
                });

    }

    private void getCountOfLike() {

        viewModel.getCountOfLikeOnExhibition(idExhibition)
                .observe(this, aBoolean -> {
                    if (aBoolean == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных о  лайках", Toast.LENGTH_SHORT).show();
                    } else {
                        textViewCountLikes.setText(aBoolean);
                    }
                });
    }

    private void getExhibitsByExhibition() {

        viewModel.getExhibitsFromExhibition(idExhibition)
                .observe(this, aBoolean -> {
                    if (aBoolean == null) {
                        Toast.makeText(getContext(), "Ошибка полученяи экпонатов", Toast.LENGTH_SHORT).show();
                    } else {
                        updateList(aBoolean);
                    }
                });
    }


}
