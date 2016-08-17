package com.dreamsofpines.flowcontrol.data.storage.preferenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class GlobalPreferences {

    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_PASSWORD = "my_password";
    private static final String FIRST_OPEN = "first_open";
    private static final String MY_NICKNAME = "my_nickname";

    private SharedPreferences mySetting;


    public static boolean isFirstOpen(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(FIRST_OPEN, false);
    }

    public static void setFirstOpen(Context context){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(FIRST_OPEN, true)
                .commit();
    }

    public static String getMyNickname() {
        return MY_NICKNAME;
    }

    public static String getMyPASSWORD(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(MY_PASSWORD,null);
    }

    public static void setMyPassword(Context context, String pass) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(MY_PASSWORD,pass)
                .commit();
    }



}
