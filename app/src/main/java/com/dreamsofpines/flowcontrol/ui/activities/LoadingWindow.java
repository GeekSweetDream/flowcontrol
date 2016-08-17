package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.preferenses.GlobalPreferences;

/**
 * Created by ThePupsick on 16.07.16.
 */
public class LoadingWindow extends AppCompatActivity {


    private Intent intent;

    private SharedPreferences mySetting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_window);

        int secondDelay = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },secondDelay * 1000);
        Context AppContext = getApplicationContext();
//        // Create settings file
//        mySetting = getSharedPreferences(GlobalPreferences.getMySettings(), Context.MODE_PRIVATE);
//        // Check the first open
        boolean hasVisited = GlobalPreferences.isFirstOpen(AppContext);
        if(!hasVisited){

            // insert layout with registration !!!

            GlobalPreferences.setFirstOpen(AppContext);
            GlobalPreferences.setMyPassword(AppContext, "78321");
//            mySetting.edit()
//                    .putBoolean("hasVisited",true)
//                    .apply();
//            mySetting.edit()
//                    .putString(GlobalPreferences.getMyPASSWORD(),"78321")
//                    .apply();
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
