package com.dreamsofpines.flowcontrol.data.storage.models;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

/**
 * Created by ThePupsick on 17.07.16.
 */
public class Cost {

    private String payment;
    private String imageService; // change on bitmap
    private String date;
    private String time;

    public Cost(String imageService, String date, String payment, String time) {
        this.imageService = imageService;
        this.date = date;
        this.payment = payment;
        this.time = time;
    }

    private String glueDate(String time, String date){
        return date + " " + time;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return glueDate(time,date);
    }

    public String getImageService() {
        return imageService;
    }
}
