package com.naosim.autoupdatecontentprovider.app.contentprovider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.naosim.autoupdatecontentprovider.app.contentprovider.database.schame.Manifests;

/**
 * Created by fujitanao on 2014/05/15.
 */
public class AutoUpdateContentDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "autoupdate_db";

    public AutoUpdateContentDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Manifests.getCreateTableSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
