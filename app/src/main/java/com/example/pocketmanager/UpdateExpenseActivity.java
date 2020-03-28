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

public class UpdateExpenseActivity extends AppCompatActivity {

    private EditText UpdateDetailET;
    private EditText UpdateCostET;

    private ExpenseDetails ExDetail;
    private DataBaseSource dataBaseSource;
    private ArrayList<ExpenseDetails> ExDetailList;

    private Calendar calendar;
    private int rId;
    private String detail,dateTime,cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);

        UpdateDetailET = (EditText) findViewById(R.id.updateDetails);
        UpdateCostET = (EditText) findViewById(R.id.updateCost);
        dataBaseSource = new DataBaseSource(this);

        rId= Integer.parseInt(getIntent().getStringExtra("rowID"));
        detail = getIntent().getStringExtra("detail").toString();
        cost = getIntent().getStringExtra("cost");
        dateTime = getIntent().getStringExtra("dateTime");

        //Toast.makeText(this,rId+" "+detail,Toast.LENGTH_LONG).show();

        UpdateDetailET.setText(detail);
        UpdateCostET.setText(cost);

    }


    //@RequiresApi(api = Build.VERSION_CODES.N)
    public void AddDetailListView(View view) {

        String detail = UpdateDetailET.getText().toString();
        String cost1 = UpdateCostET.getText().toString();

        if(detail.isEmpty())
        {
            UpdateDetailET.setError("This field can't be empty");
        }
        else if(cost.isEmpty())
        {
            UpdateCostET.setError("This field can't be empty");
        }
        else{


            int costInt1,costInt2,balance;

            costInt1 = Integer.parseInt(cost);
            costInt2 = Integer.parseInt(cost1);
            balance = dataBaseSource.getBalance(1);

            balance = balance + costInt1 - costInt2;

            if(balance<0) {
                Toast.makeText(this,"Balance is Low",Toast.LENGTH_SHORT).show();

            }
            else{
                ExDetail = new ExpenseDetails(dateTime,detail,cost1,rId);
                boolean status = dataBaseSource.updateExpense(ExDetail);
                if(status){

                    dataBaseSource.updateBalance(1,balance);
                    Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MyExpenseActivity.class));
                }
                else{
                    Toast.makeText(this,"Please Try Again",Toast.LENGTH_SHORT).show();
                }

            }



        }
        finish();


    }


}
