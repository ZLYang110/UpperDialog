package com.zlylib.upperdialog.pro;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.zlylib.upperdialog.manager.ActivityHolder;


/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public final class ActivityProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context instanceof Application) {
            ActivityHolder.init((Application) context);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
