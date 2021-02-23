package com.elysino.comicapp.sqllite;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
   private final static int version=1;
    private final static String dbName="ComicData";
    public static final String KEY_NAME = "NAME";
    private static final String TAG = "DataBase";

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase=sqLiteDatabase;
        String sql="CREATE TABLE COMIC(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,IMAGE TEXT,ALT TEXT,DAY TEXT, MONTH TEXT, YEAR TEXT,NUM INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "COMIC");
        return (int) count+1;
    }
}
