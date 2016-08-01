package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.database.CostBaseHelper;
import com.dreamsofpines.flowcontrol.data.database.CostCursorWrapper;
import com.dreamsofpines.flowcontrol.data.database.CostDbSchema.CostTable;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;
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
    private EmptyListCostFragment mEmptyListCostFragment;
    private ListCoastFragment mListCoastFragment;
    private List<Cost> mCosts;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pages);

//        RecyclerView recyclerViewVert = (RecyclerView) findViewById(R.id.recViewVert);
//        RecyclerView.LayoutManager lim = new LinearLayoutManager(this);
//        recyclerViewVert.setLayoutManager(lim);
//        recyclerViewVert.setHasFixedSize(true);

        mContext = this.getApplicationContext();
        mDatabase = new CostBaseHelper(mContext).getWritableDatabase();
        insertInDB();
        mCosts = createList();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(mCosts.size() == 0) {
            mEmptyListCostFragment = new EmptyListCostFragment();
            mFragmentTransaction.add(R.id.homeFrameLayout,mEmptyListCostFragment);
        }else {
            mListCoastFragment = new ListCoastFragment();
            mListCoastFragment.setCosts(mCosts);
            mFragmentTransaction.add(R.id.homeFrameLayout,mListCoastFragment);

        }
        mFragmentTransaction.commit();
    }

    public static ContentValues getContentValues(Cost cost){
        ContentValues values = new ContentValues();
        values.put(CostTable.Cols.COST, cost.getPayment());
        values.put(CostTable.Cols.DATE, cost.getDate());
        return values;
    }

    public void addCost(Cost cost){
        ContentValues values = getContentValues(cost);
        mDatabase.insert(CostTable.NAME,null,values);
    }

    public void insertInDB(){
        addCost(new Cost("23","04.04.2016","-20","11:23"));
        addCost(new Cost("23","03.04.2016","-2000","15:23"));
        addCost(new Cost("23","10.04.2016","-2342","10:23"));
        addCost(new Cost("23","10.04.2016","-2123","10:34"));
        addCost(new Cost("23","10.04.2016","-2","20:00"));
        addCost(new Cost("23","10.04.2016","-23","10:13"));
        addCost(new Cost("23","10.04.2016","-245","05:03"));
        addCost(new Cost("23","10.04.2016","-2","01:53"));
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
