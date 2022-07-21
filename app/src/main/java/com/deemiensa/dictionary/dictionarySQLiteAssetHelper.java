package com.deemiensa.dictionary;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class dictionarySQLiteAssetHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "Dictionary.db";
    private static final int DATABASE_VERSION = 2;
    Context context;
    public dictionarySQLiteAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
