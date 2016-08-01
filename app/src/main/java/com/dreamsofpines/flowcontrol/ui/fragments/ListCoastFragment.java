package com.dreamsofpines.flowcontrol.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;
import com.dreamsofpines.flowcontrol.ui.adapters.CostsAdapter;

import java.util.List;

/**
 * Created by ThePupsick on 24.07.16.
 */
public class ListCoastFragment extends Fragment {

    private List<Cost> mCosts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setCosts(List<Cost> costs) {
        mCosts = costs;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_cost_fragment, container, false);

        RecyclerView recyclerViewVert = (RecyclerView) view.findViewById(R.id.recViewVert);
        RecyclerView.LayoutManager lim = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewVert.setLayoutManager(lim);
        recyclerViewVert.setHasFixedSize(true);

        CostsAdapter ca = new CostsAdapter(mCosts);
        recyclerViewVert.setAdapter(ca);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
