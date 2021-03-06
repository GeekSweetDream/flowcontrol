package com.dreamsofpines.flowcontrol.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dreamsofpines.flowcontrol.data.database.CostDbSchema.CostTable;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;

/**
 * Created by ThePupsick on 31.07.16.
 */
public class CostBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "costBase.db";

    public CostBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CostTable.NAME + "(" + "_id integer primary key autoincrement, " +
                CostTable.Cols.COST + ", " +
                CostTable.Cols.DATE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static ContentValues getContentValues(Cost cost){
        ContentValues values = new ContentValues();
        values.put(CostTable.Cols.COST, cost.getPayment());
        values.put(CostTable.Cols.DATE, cost.getDate());
        return values;
    }

    public static void addCost(SQLiteDatabase mDatabase ,Cost cost){
        ContentValues values = getContentValues(cost);
        mDatabase.insert(CostTable.NAME,null,values);
    }
}
