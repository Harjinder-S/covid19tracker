package algonquin.cst2335.sing1477.storage;

import android.provider.BaseColumns;

public final class SqlLiteDB {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SqlLiteDB() {
    }

    /** Inner class that defines the table contents */
    public static class CovidDatabase implements BaseColumns {
        public static final String TABLE_NAME = "CovidTable";
        public static final String COLUMN_NAME_total_cases = "total_Case";
        public static final String COLUMN_NAME_total_fatalities = "total_fatalities";
        public static final String COLUMN_NAME_total_vaccinations = "total_vaccination";
        public static final String COLUMN_NAME_total_recoveries = "total_recoveries";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + CovidDatabase.TABLE_NAME + " (" +
                        CovidDatabase._ID + " INTEGER PRIMARY KEY," +
                        CovidDatabase.COLUMN_NAME_total_cases + " TEXT," +
                        CovidDatabase.COLUMN_NAME_total_fatalities + " TEXT," +
                        CovidDatabase.COLUMN_NAME_total_recoveries + " TEXT," +
                        CovidDatabase.COLUMN_NAME_total_vaccinations + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CovidDatabase.TABLE_NAME;

    }

}

