package com.myapps.bottomnavigationbarfragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public static Integer credit_total;
    public static Integer debit_total;
    public static int[] values;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "PaymentsTable.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createTableStatement = "CREATE TABLE " + DATABASE_NAME + "(" + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," + COLUMN_AMOUNT + " INT ," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_CATEGORY + " TEXT," + COLUMN_TYPE + " TEXT, " + COLUMN_DATE + " TEXT ," + COLUMN_TIME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @SuppressLint("Range")
    public boolean addOne(TransactionModel transactionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AMOUNT, transactionModel.getAmount());
        cv.put(COLUMN_DESCRIPTION, transactionModel.getDescription());
        cv.put(COLUMN_CATEGORY, transactionModel.getCategory());
        cv.put(COLUMN_TYPE, transactionModel.getType());
        cv.put(COLUMN_DATE, transactionModel.getDate());
        cv.put(COLUMN_TIME, transactionModel.getTime());


//        String query = "SELECT sum(Amount) from Record where Type='CREDIT'";
//        db.execSQL(query);


        long insert = db.insert(DATABASE_NAME, null, cv);
        return insert != -1;
    }

    public void method_For_Values(int[] array_ra_pumka) {
        VisualizeFragment visualize = new VisualizeFragment();
        visualize.vals = array_ra_pumka;

    }

    @SuppressLint("Range")
    public int getCredit() {
        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT SUM(Amount) as Total FROM Payments WHERE Type = 'CREDIT'", null);
        if (cursor.moveToFirst())
            credit_total = cursor.getInt(cursor.getColumnIndex("Total"));
        return credit_total;
    }

    @SuppressLint("Range")
    public int getDebit(){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT SUM(Amount) as Total1 FROM Payments WHERE Type = 'DEBIT'", null);
        if (cursor.moveToFirst())
            debit_total = cursor.getInt(cursor.getColumnIndex("Total1"));

        return debit_total;
    }


    public List<TransactionModel> getAll() {

        List<TransactionModel> returnList = new ArrayList<>();
        // String queryString = "SELECT * FROM " + PAYMENTS_HISTORY;
        String que = "SELECT * FROM " + DATABASE_NAME + " ORDER BY " + COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(que, null);

        int food_Total = 0;
        int groceries_Total = 0;
        int travel_Total = 0;
        int phone_Total = 0;
        int shopping_Total = 0;
        int education_Total = 0;
        int electricity_Total = 0;
        int bills_Total = 0;
        int housing_Total = 0;
        int fuel_Total = 0;
        int others_Total = 0;
        int health_Total = 0;

        if (cursor.moveToFirst()) {
            do {
                int transactionID = cursor.getInt(0);
                int transactionAmount = cursor.getInt(1);
                String transactionDescription = cursor.getString(2);
                String transactionCategory = cursor.getString(3);
                String transactionType = cursor.getString(4);
                String transactionDate = cursor.getString(5);
                String transactionTime = cursor.getString(6);


                switch (transactionCategory) {
                    case "FOOD":
                        food_Total += transactionAmount;
                        break;
                    case "SHOPPING":
                        shopping_Total += transactionAmount;
                        break;
                    case "PHONE":
                        phone_Total += transactionAmount;
                        break;
                    case "HEALTH":
                        health_Total += transactionAmount;
                        break;
                    case "GROCERIES":
                        groceries_Total += transactionAmount;
                        break;
                    case "TRAVEL":
                        travel_Total += transactionAmount;
                        break;
                    case "FUEL":
                        fuel_Total += transactionAmount;
                        break;
                    case "EDUCATION":
                        education_Total += transactionAmount;
                        break;
                    case "ELECTRICITY":
                        electricity_Total += transactionAmount;
                        break;
                    case "BILLS":
                        bills_Total += transactionAmount;
                        break;
                    case "HOUSING":
                        housing_Total += transactionAmount;
                        break;
                    case "OTHER":
                        others_Total += transactionAmount;
                        break;
                }

                values = new int[]{food_Total, shopping_Total, phone_Total, health_Total, groceries_Total, travel_Total, fuel_Total, education_Total, electricity_Total, bills_Total, housing_Total, others_Total};

                method_For_Values(values);


                TransactionModel transactionModel = new TransactionModel(transactionID, transactionAmount, transactionDescription, transactionCategory, transactionType, transactionDate, transactionTime);

                returnList.add(transactionModel);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();


        return returnList;

    }


}
