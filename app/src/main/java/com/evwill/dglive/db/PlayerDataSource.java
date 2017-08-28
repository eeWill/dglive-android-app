package com.evwill.dglive.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evwill.dglive.models.Player;

/**
 * Created by evan on 8/27/17.
 */

public class PlayerDataSource {

    private SQLiteDatabase mDatabase;
    private PlayerHelper mPlayerHelper;
    private Context mContext;

    public PlayerDataSource(Context context) {
        mContext = context;
        mPlayerHelper = new PlayerHelper(mContext);
    }

    public void open() {
        mDatabase = mPlayerHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public void insertPlayer(Player player) {
        mDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(PlayerHelper.COLUMN_NAME, player.getName());
            mDatabase.insert(PlayerHelper.TABLE_PLAYERS, null, values);
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }

    }

    public Cursor selectAllPlayers() {
        Cursor cursor = mDatabase.query(
            PlayerHelper.TABLE_PLAYERS,
            new String[] { PlayerHelper.COLUMN_NAME },
            null, null, null, null, null
        );

        return cursor;
    }


}
