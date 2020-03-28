package com.example.pocketmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddExpense extends AppCompatActivity {

    private EditText WriteDetailET;
    private EditText WriteCostET;

    private ExpenseDetails ExDetail;
    private DataBaseSource dataBaseSource;
    private ArrayList<ExpenseDetails> ExDetailList;

    private Calendar calendar;
    private int year,month,day,hh,mm;
    private int balance,costInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        WriteDetailET = findViewById(R.id.writeDetails);
        WriteCostET = findViewById(R.id.writeCost);
        dataBaseSource = new DataBaseSource(this);


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AddDetailListView(View view) {

        String detail = WriteDetailET.getText().toString();
        String cost = WriteCostET.getText().toString();


        //ExDetail.setExdetails(ExDetail);

        //boolean status = dataBaseSource.addExpense(ExDetail);

        if(detail.isEmpty())
        {
            WriteDetailET.setError("This field can't be empty");
        }
        else if(cost.isEmpty())
        {
            WriteCostET.setError("This field can't be empty");
        }
        else{

            if(dataBaseSource.test()) {
                balance = dataBaseSource.getBalance(1);
                costInt = Integer.parseInt(cost);

                if(costInt<= balance)
                {
                    balance = balance - costInt;
                    dataBaseSource.updateBalance(1,balance);
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                    String dateTime = currentTime+"   "+currentDate;
                    //Toast.makeText(this,dateTime,Toast.LENGTH_SHORT).show();
                    ExDetail = new ExpenseDetails(dateTime,detail,cost,0);

                    boolean status = dataBaseSource.addExpense(ExDetail);
                    if(status){
                        startActivity(new Intent(AddExpense.this,MyExpenseActivity.class));
                    }
                    else{
                        Toast.makeText(this,"Please Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(this,"Balance is Low",Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this,"Balance is 0",Toast.LENGTH_LONG).show();


        }

        finish();
    }
}
