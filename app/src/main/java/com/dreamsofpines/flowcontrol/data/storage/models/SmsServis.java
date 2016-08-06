package com.dreamsofpines.flowcontrol.data.storage.models;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.ui.activities.MainActivity;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class SmsServis extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(16)
    private void showNotification(String text){
        PendingIntent intent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0);
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("New message")
                .setContentText(text)
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.lock_open_outline)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notificationManager.notify(R.drawable.lock_open_outline,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String body = intent.getExtras().getString("sms_body");
        showNotification(body);
        return START_STICKY;
    }
}

