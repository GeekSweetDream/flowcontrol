package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.database.CostBaseHelper;
import com.dreamsofpines.flowcontrol.data.database.CostCursorWrapper;
import com.dreamsofpines.flowcontrol.data.database.CostDbSchema.CostTable;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;
import com.dreamsofpines.flowcontrol.data.storage.models.SmsService;
import com.dreamsofpines.flowcontrol.ui.adapters.CostsAdapter;
import com.dreamsofpines.flowcontrol.ui.fragments.EmptyListCostFragment;
import com.dreamsofpines.flowcontrol.ui.fragments.ListCoastFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThePupsick on 13.07.16.
 */
public class HomePages extends FragmentActivity {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private ListCoastFragment mListCoastFragment;
    private List<Cost> mCosts;
    private FragmentTransaction mFragmentTransaction;
    private View menu,card,mainCard;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TextView mTextView;

    @Override
    protected void onStart() {
        super.onStart();
        mRecyclerView = (RecyclerView) mListCoastFragment.getView().findViewById(R.id.recViewVert);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pages);

        mImageView = (ImageView) findViewById(R.id.buttonNewSms);
        mTextView = (TextView) findViewById(R.id.emptyList);

        mContext = this.getApplicationContext();
        mDatabase = new CostBaseHelper(mContext).getWritableDatabase();

        mCosts = createList();
        mListCoastFragment = new ListCoastFragment();
        mListCoastFragment.setCosts(mCosts);
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.frameForRecycleView,mListCoastFragment);
        if(mCosts.size() == 0) {
            mTextView.setVisibility(View.VISIBLE);
            insertInDB();
            insertInDB();
            insertInDB();
            insertInDB();
        }
        mFragmentTransaction.commit();

        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent mIntent = new Intent(HomePages.this,SmsService.class);
                mIntent.putExtra("sms_body","ECMC3260 23 43 as as 1670p");
                mTextView.setVisibility(View.INVISIBLE);
                mContext.startService(mIntent);
                CostsAdapter adapter = (CostsAdapter) mRecyclerView.getAdapter();
                adapter.setCostList(createList());
                adapter.notifyDataSetChanged();
                return false;
            }
        });

//        mainCard = (View) findViewById(R.id.cardLayout);
//
//        mainCard.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                    /*if (listCards.getVisibility() == View.INVISIBLE){
//                        listCards.setVisibility(View.VISIBLE);
//                        listCards.setClickable(true);
//                    } else {
//                        listCards.setVisibility(View.INVISIBLE);
//                        listCards.setClickable(false);
//                    }*/
//                return false;
//            }
//        });

}



    public void insertInDB(){
        CostBaseHelper.addCost(mDatabase,new Cost("23","04.04.2016","-20","11:23"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","03.04.2016","-2000","15:23"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-2342","10:23"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-2123","10:34"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-2","20:00")) ;
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-23","10:13"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-245","05:03"));
        CostBaseHelper.addCost(mDatabase, new Cost("23","10.04.2016","-2","01:53"));
    }

    private CostCursorWrapper queryCosts(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CostTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                "_id DESC"
                );
        return new CostCursorWrapper(cursor);
    }

    private List<Cost> createList(){
        List<Cost> res = new ArrayList<>();
        CostCursorWrapper cursor = queryCosts(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                res.add(cursor.getCost());
                cursor.moveToNext();
            }
        }finally{
                cursor.close();
            }
        return res;
    }

}
