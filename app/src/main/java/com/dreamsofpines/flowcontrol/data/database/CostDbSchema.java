package com.dreamsofpines.flowcontrol.data.database;

/**
 * Created by ThePupsick on 31.07.16.
 */
public class CostDbSchema {
    public static final class CostTable{
        public static final String NAME ="costs";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String COST = "cost";
            public static final String DATE = "cost_Date";
        }
    }
}
