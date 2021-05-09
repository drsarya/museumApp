package com.example.museums.view.fragments.common.detailedExhibitWithoutListeners;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerShare;
import com.example.museums.view.services.Listeners.onTouchListeners.OnTouchListenerScrollViewSwipeLeftRight;

import static com.example.museums.view.ConstantKeys.AUTHOR_KEY;
import static com.example.museums.view.ConstantKeys.DATE_KEY;
import static com.example.museums.view.ConstantKeys.DESCRIPTION_KEY;
import static com.example.museums.view.ConstantKeys.ID_EXHIBIT_KEY;
import static com.example.museums.view.ConstantKeys.ID_USER_KEY;
import static com.example.museums.view.ConstantKeys.IMAGE_KEY;
import static com.example.museums.view.ConstantKeys.NAME_KEY;


public class DetailedExhibitWithoutListenersBackPressed extends Fragment {
     private ScrollView view;
    private LinearLayout ll;
    private ImageButton like;
    private String name, author, date, description;
    private Integer idExhibit;
    private String image;
    public ImageButton closeImageButton;
    private ImageView mainImageImageView, shareExhibit;
    private TextView nameTextView, authorTextView, dateTextView, descriptionTextView;
    private boolean state = false;
    private TextView textViewCountLikes;
    private Integer userId;
    private DetailedExhibitWithoutListenerViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit_view_page, container, false);
        getArgumentsFromBundle();
        initView(rootView);
        setListeners();
        setData();
        state = true;

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public DetailedExhibitWithoutListenersBackPressed newInstance(Integer idExhibit, Integer userId, String image, String name, String author, String date, String description) {
        final DetailedExhibitWithoutListenersBackPressed myFragment = new DetailedExhibitWithoutListenersBackPressed();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_KEY, date);
        args.putString(AUTHOR_KEY, author);
        args.putString(DESCRIPTION_KEY, description);
        if (userId == null) {
            args.putInt(ID_USER_KEY, -1);
        } else {
            args.putInt(ID_USER_KEY, userId);
        }
        args.putInt(ID_EXHIBIT_KEY, idExhibit);
        args.putString(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }


    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            author = getArguments().getString(AUTHOR_KEY);
            date = getArguments().getString(DATE_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            image = getArguments().getString(IMAGE_KEY);
            idExhibit = getArguments().getInt(ID_EXHIBIT_KEY);
            userId = getArguments().getInt(ID_USER_KEY);
        }
    }

    private void setData() {
        Glide.with(getContext())
                .load(image)
                .into(mainImageImageView);
        authorTextView.setText(author);
        dateTextView.setText(date);
        descriptionTextView.setText(description);
        nameTextView.setText(name);
    }


    private void initView(View rootView) {
        viewModel = ViewModelProviders.of(this).get(DetailedExhibitWithoutListenerViewModel.class);

        shareExhibit = rootView.findViewById(R.id.detailed_exhibit_without_listener_share_image_view);
        textViewCountLikes = rootView.findViewById(R.id.detailed_exhibit_view_page_count_likes_text_view);
        ll = rootView.findViewById(R.id.detailed_exhibit_option_pane_lin_lay);
        view = rootView.findViewById(R.id.detailed_exhibit_description_scroll_view);
        like = rootView.findViewById(R.id.detailed_exhibit_view_page_like_btn);
        mainImageImageView = rootView.findViewById(R.id.detailed_exhibit_main_image_image_view);
        nameTextView = rootView.findViewById(R.id.detailed_exhibit_name_of_paint_text_view);
        authorTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_author_text_view);
        dateTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_date_text_view);
        descriptionTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_description);
        closeImageButton = rootView.findViewById(R.id.detailed_exhb_view_pager);

        if (userId != -1) {
            getUserLike();
        }
        getCountOfLike();
    }

    private String createMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(author + "     " + name + "\n");
        stringBuilder.append(date + "\n");
        stringBuilder.append(description + "\n");
        stringBuilder.append("@App \"Выставочный зал\" by Darya" + "\n");

        return stringBuilder.toString();
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {
        if (userId != null && userId != -1) {
            like.setOnClickListener(v -> insertLike());
        }
        getArguments().clear();
        view.setOnTouchListener(new OnTouchListenerScrollViewSwipeLeftRight(ll, true));
        closeImageButton.setOnClickListener(v1 -> getActivity().onBackPressed());

        shareExhibit.setOnClickListener(new ClickListenerShare(getActivity(), createMessage(), image));
    }


    private void insertLike() {
        viewModel.insertLike(idExhibit, userId)
                .observe(this, aBoolean -> {
                    getUserLike();
                    getCountOfLike();
                });
    }

    private void getUserLike() {
        viewModel.getUserLike(idExhibit, userId)
                .observe(this, aBoolean -> {
                    if (aBoolean == null) {
                        like.setColorFilter(getActivity().getResources().getColor(R.color.brown));
                    } else {
                        like.setColorFilter(getActivity().getResources().getColor(R.color.pink));
                    }
                });
    }

    private void getCountOfLike() {
        viewModel.getCountOfLikeOnExhibition(idExhibit)
                .observe(this, aBoolean -> {
                    if (aBoolean == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        textViewCountLikes.setText(aBoolean);
                    }
                });
    }
}
