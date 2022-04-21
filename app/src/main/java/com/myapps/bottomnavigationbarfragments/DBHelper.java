package com.myapps.bottomnavigationbarfragments;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper {
    Context ctx;

    public DBHelper(Context context) {

        super(context, "Bill", null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table  payments(name varchar(20),amount int(20))");
        Log.i("db", "table created");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "name");
        contentValues.put("amount", 0);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists payments");
    }

    public boolean insert(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("amount", 0);
        long result = DB.insert("payments", null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public boolean delete() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from payments", null);
        if (cursor.getCount() > 0) {
            long result = DB.delete("payments", null, null);
            if (result == -1)
                return false;
            return true;
        } else {
            return false;
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from payments", null);
        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            Cursor c = null;
            return c;
        }
    }
    public Cursor selectAll(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from payments where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            Cursor c = null;
            return c;
        }
    }

    public void add(int amount1, String name1) {
        int initialAmount;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from payments ", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals(name1)) {
                    initialAmount = cursor.getInt(1);
                    int updatedAmount = initialAmount + amount1;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", name1);
                    contentValues.put("amount", updatedAmount);
                    DB.update("payments", contentValues, "name=?", new String[]{name1});
                    Toast.makeText(ctx, "Paid sucessfully", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        }


    }
}
