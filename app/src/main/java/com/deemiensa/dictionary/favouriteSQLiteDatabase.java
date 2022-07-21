package com.deemiensa.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class favouriteSQLiteDatabase {

    private static final int version = 3;
    private static final String name = "favdata.db";
    private static final String tablename = "favtab";
    private static final String col_id = "_id";
    private static final String col_idm = "id";
    private static final String col_word = "word";
    private static final String col_type = "type";
    private static final String col_defn = "defn";

    private favdatabasemaker openHelper;
    private SQLiteDatabase database, database2;

    public favouriteSQLiteDatabase(Context context) {
        openHelper = new favdatabasemaker(context);
        database = openHelper.getWritableDatabase();
        database2 = openHelper.getReadableDatabase();
    }

    public void save(int Id, String word, String type, String defn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_idm, Id);
        contentValues.put(col_word, word);
        contentValues.put(col_type, type);
        contentValues.put(col_defn, defn);
        database.insert(tablename, null, contentValues);
    }

    public void delete(int Id){
        String query = "select * from favtab where id = " + Id;
        Cursor cursor = this.database2.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                database2.delete(tablename, col_idm + "=" + Id, null);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    public String[] allwords(){
        String query = "Select * from favtab";
        Cursor cursor = this.database2.rawQuery(query, null);
        ArrayList<String> wordTerms = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                wordTerms.add(word);
            }while(cursor.moveToNext());
        }
        cursor.close();
        String[] dictionaryWords = new String[wordTerms.size()];
        dictionaryWords = wordTerms.toArray(dictionaryWords);
        return dictionaryWords;
    }

    public dictionaryModel getBywd(String wd) {
        dictionaryModel dt = null;
        String query = "select * from favtab where word = ?; ";
        Cursor cursor = this.database2.rawQuery(query, new String[] {wd});
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("defn"));
                dt = new dictionaryModel(id,word,type,meaning);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dt;
    }

    public boolean exists(int Id) {
        int cnt=0;
        String query = "select * from favtab where id = " + Id;
        Cursor cursor = this.database2.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                cnt++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        if(cnt>0)
            return true;
        else
            return false;
    }

    private class favdatabasemaker extends SQLiteOpenHelper
    {
        public favdatabasemaker(Context context) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate (SQLiteDatabase database){
            database.execSQL(
                    "CREATE TABLE " + tablename + "( "
                            + col_id + " INTEGER PRIMARY KEY, "
                            + col_idm + " INTEGER, "
                            + col_word + " TEXT, "
                            + col_type + " TEXT, "
                            + col_defn + " TEXT )"
            );
        }

        @Override
        public void onUpgrade (SQLiteDatabase database,int i, int i1){
            database.execSQL("DROP TABLE IF EXISTS " + tablename);
            onCreate(database);
        }
    }
}
