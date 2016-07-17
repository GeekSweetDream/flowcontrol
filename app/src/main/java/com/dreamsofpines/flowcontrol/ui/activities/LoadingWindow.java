package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.dreamsofpines.flowcontrol.R;

/**
 * Created by ThePupsick on 16.07.16.
 */
public class LoadingWindow extends AppCompatActivity {

    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_PASSWORD = "my_password";
    private static final String MY_NICKNAME = "my_nickname";
    private Intent intent;

    public static String getMyNickname() {
        return MY_NICKNAME;
    }

    public static String getMyPASSWORD() {
        return MY_PASSWORD;
    }

    private SharedPreferences mySetting;

    public static String getMySettings() {
        return MY_SETTINGS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_window);

        int secondDelay = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },secondDelay * 1000);

        // Create settings file
        mySetting = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        // Check the first open
        boolean hasVisited = mySetting.getBoolean("hasVisited", false);
        if(!hasVisited){

            // insert layout with registration !!!
            mySetting.edit().putBoolean("hasVisited",true).apply();
            mySetting.edit().putString(MY_PASSWORD,"78321").apply();

            intent = new Intent(LoadingWindow.this,HomePages.class);

        } else {
            intent = new Intent(LoadingWindow.this,MainActivity.class);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
