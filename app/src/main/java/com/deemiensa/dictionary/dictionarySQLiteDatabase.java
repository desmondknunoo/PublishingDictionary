package com.deemiensa.dictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class dictionarySQLiteDatabase {
    private static dictionarySQLiteAssetHelper dbHelper;
    private SQLiteDatabase db;

    public dictionarySQLiteDatabase(Context context) {
        dbHelper=new dictionarySQLiteAssetHelper(context);
        db=dbHelper.getReadableDatabase();
    }

    public String[] allwords(){
        String query = "Select * from words";
        Cursor cursor = this.db.rawQuery(query, null);
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
    public dictionaryModel getById(int Id){
        dictionaryModel dt = null;
        String query = "select * from words where _id = " + Id;
        Cursor cursor = this.db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("defn"));
                dt = new dictionaryModel(id,word,type,meaning);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dt;
    }
}
