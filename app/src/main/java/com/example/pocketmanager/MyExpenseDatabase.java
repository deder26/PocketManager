package com.example.pocketmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyExpenseDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expense_database";
    public static final int VERSION = 2;

    public static final String TABLE_NAME = "expense_details_table";
    public static final String COL_ID = "tbl_id";
    public static final String COL_DATE_TIME = "tbl_date_time";
    public static final String COL_DETAILS = "tbl_expense_detail";
    public static final String COL_COST = "tbl_cost";

    public static final String TABLE_BALANCE = "balance_table";
    public static final String COL_BID = "tbl_id";
    public static final String COL_BALANCE = "tbl_balance";


    public static final String CREATE_TABLE = "create table "+TABLE_NAME+
            "("+COL_ID+" integer primary key , "+
            COL_DATE_TIME+" text , " +
            COL_DETAILS+" text , " +
            COL_COST+" text);";

    public static final String CREATE_BL_TABLE = "create table "+TABLE_BALANCE+
            "("+COL_BID+" integer primary key , "+
            COL_BALANCE+" integer);";


    public MyExpenseDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_BL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_BALANCE);
        onCreate(sqLiteDatabase);

    }
}
