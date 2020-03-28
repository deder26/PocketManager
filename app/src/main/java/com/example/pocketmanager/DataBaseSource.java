package com.example.pocketmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseSource {

    private MyExpenseDatabase expenseDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private ExpenseDetails expenseDetails;

    public DataBaseSource(Context context) {

        expenseDatabase = new MyExpenseDatabase(context);
    }

    public void open(){

        sqLiteDatabase = expenseDatabase.getWritableDatabase();

    }

    public void close(){

        sqLiteDatabase.close();

    }

    public boolean addBalance(int balance){
        this.open();

        ContentValues values= new ContentValues();
        values.put(expenseDatabase.COL_BALANCE,balance);
        long i= sqLiteDatabase.insert(expenseDatabase.TABLE_BALANCE,null,values);
        this.close();

        if(i>0) return true;
        else return false;

    }

    public int getBalance(int id){

        this.open();
        Cursor cursor = sqLiteDatabase.query(expenseDatabase.TABLE_BALANCE,null,null,null,null,null,null);
        cursor.moveToNext();
        int bl = cursor.getInt(cursor.getColumnIndex(expenseDatabase.COL_BALANCE));
        cursor.close();
        this.close();
        return bl;

    }

    public boolean updateBalance(int id,int balance){

        this.open();

        ContentValues values= new ContentValues();
        values.put(expenseDatabase.COL_BALANCE,balance);

        long id1 = sqLiteDatabase.update(expenseDatabase.TABLE_BALANCE,values,expenseDatabase.COL_ID+" = ?",new String[]{String.valueOf(id)});
        this.close();

        if(id1>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean test(){
        this.open();
        Cursor cursor = sqLiteDatabase.query(expenseDatabase.TABLE_BALANCE,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0) {
                cursor.close();
                this.close();
                return true;
            }
            else{
            cursor.close();
            this.close();
            return  false;
        }

    }


    public boolean addExpense(ExpenseDetails expenseDetail){

        this.open();

        ContentValues values = new ContentValues();

        values.put(expenseDatabase.COL_DATE_TIME,expenseDetail.getDateTime());
        values.put(expenseDatabase.COL_DETAILS,expenseDetail.getExpenseDetails());
        values.put(expenseDatabase.COL_COST,expenseDetail.getCost());

        long id = sqLiteDatabase.insert(expenseDatabase.TABLE_NAME,null,values);
        this.close();

        if(id>0){
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<ExpenseDetails> getAllExpenseDetails(){

        ArrayList<ExpenseDetails> exDetailList = new ArrayList<>();

        this.open();
        Cursor cursor = sqLiteDatabase.query(expenseDatabase.TABLE_NAME,null,null,null,null,null,expenseDatabase.COL_ID+" DESC");
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){
            for (int i=0; i<cursor.getCount();i++){
                int id = cursor.getInt(cursor.getColumnIndex(expenseDatabase.COL_ID));
                String dataTime = cursor.getString(cursor.getColumnIndex(expenseDatabase.COL_DATE_TIME));
                String detail = cursor.getString(cursor.getColumnIndex(expenseDatabase.COL_DETAILS));
                String cost = cursor.getString(cursor.getColumnIndex(expenseDatabase.COL_COST));

                expenseDetails = new ExpenseDetails(dataTime,detail,cost,id);
                exDetailList.add(expenseDetails);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return exDetailList;

    }

    public boolean deleteExpense(int rowId){
        this.open();
        int id= sqLiteDatabase.delete(expenseDatabase.TABLE_NAME,expenseDatabase.COL_ID+" = ?",new String[]{String.valueOf(rowId)});
        this.close();
        if(id>0) return true;
        else return false;

    }

    public boolean updateExpense(ExpenseDetails expenseDetail){

        this.open();

        ContentValues values = new ContentValues();

        values.put(expenseDatabase.COL_DATE_TIME,expenseDetail.getDateTime());
        values.put(expenseDatabase.COL_DETAILS,expenseDetail.getExpenseDetails());
        values.put(expenseDatabase.COL_COST,expenseDetail.getCost());

        int rId = expenseDetail.getId();

        long id = sqLiteDatabase.update(expenseDatabase.TABLE_NAME,values,expenseDatabase.COL_ID+" = ?",new String[]{String.valueOf(rId)});
        this.close();

        if(id>0){
            return true;
        }else{
            return false;
        }

    }
}
