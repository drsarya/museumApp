//package com.example.museums.view.activities.common.Registration;
//
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.museums.API.services.impl.UserFacadeImpl;
//
//
//public class QueryRegistration {
//    private Registration activity;
//    private UserFacadeImpl userFacade;
//    private MuseumDao memsDao;
//
//    public QueryRegistration(Registration activity) {
//        this.activity = activity;
//    }
//
//    public void onSuccess() {
//        Toast.makeText(activity.getApplicationContext(),
//                "Успешная регистрация", Toast.LENGTH_SHORT).show();
//        activity.progressBar.setVisibility(View.GONE);
//    }
//
//    public void onError() {
//        Toast.makeText(activity.getApplicationContext(),
//                "Пользователь с  введёнными данными уже существует", Toast.LENGTH_SHORT).show();
//        activity.progressBar.setVisibility(View.GONE);
//    }
//
//    public void getQuery(String login, String password, boolean type) throws Exception {
//
//        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
//        activity.progressBar.setVisibility(View.VISIBLE);
//        userFacade = new UserFacadeImpl(memsDao, this);
//        userFacade.insertUser(login, password, type);
//
//    }
//}
