package com.example.simpledatabaseex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krishna on 25/2/17.
 */

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Example.db";
    public static final String DATABASE_TABLE = "Example_db";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "PHONE_NO";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(ID INTEGER" +
                " PRIMARY KEY AUTOINCREMENT ," +
                "NAME TEXT, SURNAME TEXT,PHONE_NO INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + DATABASE_TABLE);
        onCreate(db);
    }

    public boolean insertData(String name, String surname, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, phone);

        long res = db.insert(DATABASE_TABLE, null, contentValues);
        if (res == -1) {
            return false;
        } else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from "+DATABASE_TABLE,null);
        return res;
    }

    public boolean updateData(String id,String name, String surname, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, phone);
        db.update(DATABASE_TABLE,contentValues,"ID = ?",new String[]{ id } );
        return true;
    }

  public Integer deleteData(String id){
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete(DATABASE_TABLE,"ID = ?",new String[]{ id });

  }
}
