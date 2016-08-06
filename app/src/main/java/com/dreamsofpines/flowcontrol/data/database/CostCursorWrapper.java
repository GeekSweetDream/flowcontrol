package com.dreamsofpines.flowcontrol.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.dreamsofpines.flowcontrol.data.database.CostDbSchema.CostTable;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;

/**
 * Created by ThePupsick on 31.07.16.
 */
public class CostCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CostCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Cost getCost(){
        String payment = getString(getColumnIndex(CostTable.Cols.COST));
        String date = getString(getColumnIndex(CostTable.Cols.DATE));

        Cost cost = new Cost();
        cost.setPayment(payment);
        cost.setDate(date);
        return cost;
    }
}
