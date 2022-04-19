package com.myapps.bottomnavigationbarfragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Payments";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String PRIMARY_KEY = "payments_pk";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "PaymentsTable.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createTableStatement = "CREATE TABLE " + DATABASE_NAME + "(" + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," + COLUMN_AMOUNT + " INT ," +  COLUMN_DESCRIPTION + " TEXT," + COLUMN_CATEGORY + " TEXT,"  + COLUMN_TYPE + " TEXT, " + COLUMN_DATE + " TEXT ," + COLUMN_TIME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    
    public boolean addOne(TransactionModel transactionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AMOUNT, transactionModel.getAmount());
        cv.put(COLUMN_DESCRIPTION, transactionModel.getDescription());
        cv.put(COLUMN_CATEGORY, transactionModel.getCategory());
        cv.put(COLUMN_TYPE, transactionModel.getType());
        cv.put(COLUMN_DATE, transactionModel.getDate());
        cv.put(COLUMN_TIME, transactionModel.getTime());

        long insert = db.insert(DATABASE_NAME, null, cv);
        return insert != -1;
    }

    public List<TransactionModel> getAll() {

        List<TransactionModel> returnList = new ArrayList<>();
        // String queryString = "SELECT * FROM " + PAYMENTS_HISTORY;
        String que = "SELECT * FROM " + DATABASE_NAME + " ORDER BY " + COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(que, null);





        if (cursor.moveToFirst()) {
            do {
                int transactionID = cursor.getInt(0);
                int transactionAmount = cursor.getInt(1);
                String transactionDescription = cursor.getString(2);
                String transactionCategory = cursor.getString(3);
                String transactionType = cursor.getString(4);
                String transactionDate = cursor.getString(5);
                String transactionTime = cursor.getString(6);

                TransactionModel transactionModel = new TransactionModel(transactionID, transactionAmount, transactionDescription, transactionCategory, transactionType, transactionDate, transactionTime);

                returnList.add(transactionModel);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();


        return returnList;

    }




}
