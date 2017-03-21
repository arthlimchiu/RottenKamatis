package arthlimchiu.com.rottenkamatis.data.local;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class MoviesTable {

    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_POSTER_URL = "poster_url";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_TITLE + " text,"
            + COLUMN_POSTER_URL + " text"
            + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database) {
        // Put upgrade statements here
    }
}
