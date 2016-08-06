package com.dreamsofpines.flowcontrol.data.storage.preferenses;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class GlobalPreferences {

    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_PASSWORD = "my_password";
    private static final String MY_NICKNAME = "my_nickname";

    private SharedPreferences mySetting;

    public static String getMySettings() {
        return MY_SETTINGS;
    }

    public static String getMyNickname() {
        return MY_NICKNAME;
    }

    public static String getMyPASSWORD() {
        return MY_PASSWORD;
    }


}
