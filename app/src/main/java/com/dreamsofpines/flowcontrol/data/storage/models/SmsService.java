package com.dreamsofpines.flowcontrol.data.storage.models;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.ui.activities.MainActivity;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class SmsService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(16)
    private void showNotification(String text){
        PendingIntent intent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class), 0);
        Context context = getApplicationContext();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker("Money")
                .setContentTitle("New message")
                .setContentText(text)
                .addAction(R.drawable.lock_open_outline,"home",intent)
                .addAction(R.drawable.lock_open_outline,"dosug",intent)
                .addAction(R.drawable.lock_open_outline,"123",intent)
                .setSmallIcon(R.drawable.lock_open_outline)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.ledARGB = Color.GREEN;
        notification.ledOffMS = 0;
        notification.ledOnMS = 1;
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.flags |= Notification.FLAG_INSISTENT;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS;
        notification.sound = uri;
        notificationManager.notify(R.drawable.lock_open_outline,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String body = intent.getExtras().getString("sms_body");
        showNotification(body);
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

