package com.dreamsofpines.flowcontrol.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;

import java.util.List;

/**
 * Created by ThePupsick on 18.07.16.
 */
public class CostsAdapter extends RecyclerView.Adapter<CostsAdapter.CostsViewHolder> {


    public class CostsViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mImageService;
        protected TextView mPayment;
        protected TextView mDate;

        public CostsViewHolder(View itemView) {
            super(itemView);

            mImageService = (ImageView) itemView.findViewById(R.id.serviceLogo);
            mPayment = (TextView) itemView.findViewById(R.id.costs);
            mDate = (TextView) itemView.findViewById(R.id.dateCosts);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private List<Cost> mCostList;


    public CostsAdapter(List<Cost> costList) {
        mCostList = costList;
    }



    @Override
    public CostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_layout,parent,false);
        return new CostsViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CostsViewHolder holder, int position) {
        Cost mCost = mCostList.get(position);
        //holder.mImageService.setBackground(mCost.getImageService());
        holder.mPayment.setText(mCost.getPayment());
        holder.mDate.setText(mCost.getDate());
    }

    @Override
    public int getItemCount() {
        return mCostList.size();
    }
}
