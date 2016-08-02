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

    public Cost() {
        payment = "";
        imageService = "";
        date = "";
    }

    public Cost(String imageService, String date, String payment, String time) {
        this.imageService = imageService;
        this.date = date;
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }

    public String getImageService() {
        return imageService;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
