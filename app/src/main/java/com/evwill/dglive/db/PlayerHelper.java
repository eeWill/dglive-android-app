package com.evwill.dglive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerHelper extends SQLiteOpenHelper {

    public static final String TABLE_PLAYERS = "PLAYERS";

    private static final String DB_NAME = "dglive.db";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "_ID";
    public static final String COLUMN_NAME = "NAME";

    private static final String DB_CREATE =
        "CREATE TABLE " + TABLE_PLAYERS + " (" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_NAME + " TEXT)";

    public PlayerHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
