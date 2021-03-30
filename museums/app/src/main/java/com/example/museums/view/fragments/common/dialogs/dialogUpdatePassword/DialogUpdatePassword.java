//package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//
//import com.example.museums.R;
//import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;
//
//import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
//
//public class DialogUpdatePassword extends DialogFragment {
//
//    private EditText oldPassEditText;
//    private EditText newPassEditText;
//    private TextFieldBoxes oldPassTextFieldBoxes;
//    private TextFieldBoxes newPassTextFieldBoxes;
//    private Button updateBtn;
//    public ProgressBar progressBar;
//    private String loginMuseum;
//    public static final String ID_MUSEUM_KEY = "login_key";
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View rootView =
//                inflater.inflate(R.layout.dialog_change_password, container, false);
//        initViews(rootView);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            loginMuseum = bundle.getString(ID_MUSEUM_KEY);
//        }
//        setListeners();
//        return rootView;
//    }
//
//    private void initViews(View rootView) {
//        oldPassEditText = rootView.findViewById(R.id.dialog_change_pass_old_pass_edit_text);
//        newPassEditText = rootView.findViewById(R.id.dialog_change_pass_new_pass_edit_text);
//        oldPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_old_pass_text_field_boxes);
//        newPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_new_pass_text_field_boxes);
//        updateBtn = rootView.findViewById(R.id.dialog_change_pass_btn);
//        progressBar = rootView.findViewById(R.id.dialog_change_pass_progress_bar);
//    }
//
//
//    private void setListeners() {
//        oldPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(oldPassTextFieldBoxes));
//        newPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(newPassTextFieldBoxes));
//        updateBtn.setOnClickListener(v -> {
//            if (!oldPassEditText.getText().toString().isEmpty() && !newPassEditText.getText().toString().isEmpty() &&
//                    !oldPassTextFieldBoxes.isOnError() && !newPassTextFieldBoxes.isOnError() && loginMuseum != null) {
//                QueryUpdatePassword updatePassword = new QueryUpdatePassword(this);
//                try {
//                    updatePassword.getQuery(loginMuseum, oldPassEditText.getText().toString(), newPassEditText.getText().toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
