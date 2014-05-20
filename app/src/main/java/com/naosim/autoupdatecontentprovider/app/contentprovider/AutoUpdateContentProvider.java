package com.naosim.autoupdatecontentprovider.app.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.naosim.autoupdatecontentprovider.app.contentprovider.database.AutoUpdateContentDBHelper;
import com.naosim.autoupdatecontentprovider.app.contentprovider.database.schame.Manifests;

public class AutoUpdateContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.naosim.autoupdatecontentprovider";
    static final String URI = "content://" + AUTHORITY;

    private AutoUpdateContentDBHelper dbHelper;
    @Override
    public boolean onCreate() {
        dbHelper = new AutoUpdateContentDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] selections, String s, String[] strings2, String s2) {
        return dbHelper.getWritableDatabase().query(Manifests.TABLE_NAME, selections, s, strings2, s2, null, null);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        dbHelper.getWritableDatabase().insert(Manifests.TABLE_NAME, null, contentValues);
        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return dbHelper.getWritableDatabase().delete(Manifests.TABLE_NAME, s, strings);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return dbHelper.getWritableDatabase().update(Manifests.TABLE_NAME, contentValues, s, strings);
    }
}
