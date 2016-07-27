package com.dreamsofpines.flowcontrol.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;
import com.dreamsofpines.flowcontrol.ui.adapters.CostsAdapter;
import com.dreamsofpines.flowcontrol.ui.fragments.ListCoastFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThePupsick on 13.07.16.
 */
public class HomePages extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pages);

        RecyclerView recyclerViewVert = (RecyclerView) findViewById(R.id.recViewVert);
        RecyclerView.LayoutManager lim = new LinearLayoutManager(this);
        recyclerViewVert.setLayoutManager(lim);
        recyclerViewVert.setHasFixedSize(true);


        CostsAdapter ca = new CostsAdapter(createList());
        recyclerViewVert.setAdapter(ca);


    }

    private List<Cost> createList(){
        List<Cost> res = new ArrayList<>();
        res.add(new Cost("23","03.04.2016","-2000","15:23"));
        res.add(new Cost("23","04.04.2016","-20","11:23"));
        res.add(new Cost("23","10.04.2016","-2","10:23"));
        res.add(new Cost("23","10.04.2011","-1234","00:03"));
        res.add(new Cost("23","10.04.2016","-5576","16:00"));
        return res;
    }

}
