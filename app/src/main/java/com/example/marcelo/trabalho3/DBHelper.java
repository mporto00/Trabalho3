package com.example.marcelo.trabalho3;

/**
 * Created by Marcelo on 19/06/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BDtrab3.db";
    public static final String PLACES_TABLE_NAME = "Places";
    public static final String PLACES_COLUMN_NAME = "name";
    public static final String PLACES_COLUMN_DESCRIPTION = "description";
    public static final String PLACES_COLUMN_FAVORITE = "favorite";
    public static final String PLACES_COLUMN_PIC = "pic";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table places " +
                        "(name text primary key,favorite text,pic int, description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS places");
        onCreate(db);
    }

    public void destroy() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS places");
        onCreate(this.getWritableDatabase());
    }

    public boolean insertPlace (String name, String favorite, int pic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("favorite", favorite);
        contentValues.put("pic", pic);
        contentValues.put("description", description);
        db.insert("places", null, contentValues);
        return true;
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from places where name='"+name+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PLACES_TABLE_NAME);
        return numRows;
    }

    public boolean updatePlace (String name, String favorite, String pic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("favorite", favorite);
        //contentValues.put("pic", pic);
        contentValues.put("description", description);
        db.update("places", contentValues, "name = ? ", new String[] { name } );
        return true;
    }

    public Integer deletePlace (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("places",
                "name = ? ",
                new String[] { name });
    }

    public ArrayList<String> getAllPlaces() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from places", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PLACES_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}