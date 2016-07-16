package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.dreamsofpines.flowcontrol.R;

/**
 * Created by ThePupsick on 16.07.16.
 */
public class LoadingWindow extends AppCompatActivity {

    private static final String MY_SETTINGS = "my_settings";
    private SharedPreferences mySetting;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.loadingwindow);

        // Create settings file
         mySetting = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);

        // Check the first open
        boolean hasVisited = mySetting.getBoolean("hasVisited", false);
        if(!hasVisited){
            // insert layout with registration !!!
            mySetting.edit().putBoolean("hasVisited",true).commit();

        } else {
            Intent intent = new Intent(LoadingWindow.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
