package com.example.museums.view.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.R;
import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.view.services.ConfigEncrypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static androidx.core.util.ObjectsCompat.hash;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final MuseumDao memsDao = ((AppDelegate) getApplication()).getMuseumDb().museumDao();





        //        for (int i = 0; i < 3 ; i++) {
//            final User u1 = new User();
//            u1.login = "1111";
//            u1.password = Integer.toString(i);
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    memsDao.insertUser(u1);
//                }
//            });
//        }
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<User> users = memsDao.getUsers();
//                for (int i = 0; i < users.size(); i++) {
//                    System.out.println(users.get(i).id + " " + users.get(i).login + " " + users.get(i).password);
//                }
//            }
//        });


    }
}