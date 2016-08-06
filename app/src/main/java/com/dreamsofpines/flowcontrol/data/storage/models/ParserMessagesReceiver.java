package com.dreamsofpines.flowcontrol.data.storage.models;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;

/**
 * Created by ThePupsick on 06.08.16.
 */
public class ParserMessagesReceiver extends BroadcastReceiver {

    public static final String ACTION = "android.provider.Telephony.SMS_RECIEVED";


    @Override
    @TargetApi(15)
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getAction() != null &&
                ACTION.compareToIgnoreCase(intent.getAction()) == 0){
            Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[pduArray.length];
            int curentVersion = Build.VERSION.SDK_INT;
            for(int i = 0; i <pduArray.length; ++i){
                if(curentVersion >= 23) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i], "3gpp");
                }else{
                    messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
                }
            }

            String smsFrom = messages[0].getDisplayOriginatingAddress();
            if(smsFrom.equalsIgnoreCase("900")){
                StringBuilder bodyText = new StringBuilder();
                for(int i = 0; i < messages.length; ++i){
                    bodyText.append(messages[i].getDisplayMessageBody());
                }
                String body = bodyText.toString();
                Intent mIntent = new Intent(context,SmsServis.class);
                mIntent.putExtra("sms_body",body);
                context.startService(mIntent);

                abortBroadcast();
            }

        }
    }
}
