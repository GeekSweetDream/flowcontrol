package com.dreamsofpines.flowcontrol.data.storage.models;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.database.CostBaseHelper;
import com.dreamsofpines.flowcontrol.ui.activities.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class SmsService extends Service {

    private static final int NOTIFY_ID = 101;
    private String numberCard, data, count;
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
                .setTicker("New Costs")
                .setContentTitle("You have new cost")
                .setContentText("You pay " + text + "! Where?")
                .addAction(R.drawable.lock_open_outline,"home",intent)
                .addAction(R.drawable.lock_open_outline,"dosug",intent)
                .addAction(R.drawable.lock_open_outline,"123",intent)
                .setSmallIcon(R.drawable.lock_open_outline)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.flags |= Notification.FLAG_INSISTENT;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.sound = uri;

        notificationManager.notify(NOTIFY_ID,notification);

    }


    public String[] findMainInformation(String text){
        String[] arrayStr = text.split(" ");
        String[] answer = new String[4];
        if(isCardInformation(arrayStr[0])){
            answer[0] = arrayStr[0].substring(arrayStr[0].length() - 5);
            answer[1] = arrayStr[1].concat(arrayStr[2]);
            if(isCost(arrayStr[4])){
                answer[2] = arrayStr[4];
            }else{
                answer[2] = arrayStr[5];
            }
        }
        return answer;
    }

    private boolean isCardInformation(String str){
        Pattern pattern = Pattern.compile("^.+[0-9]{4}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private boolean isCost(String str){
        Pattern pattern = Pattern.compile("^[0-9]+\\w$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String body = intent.getExtras().getString("sms_body");
        String[] costNew = findMainInformation(body);
        if(costNew[0].length() != 0) {
            SQLiteDatabase mDb = new  CostBaseHelper(getApplicationContext()).getWritableDatabase();
            CostBaseHelper.addCost(mDb, new Cost("23", costNew[1], "-"+costNew[2], "23123"));
            showNotification(costNew[2]);
            mDb.close();
        }
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

