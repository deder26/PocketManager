package com.example.pocketmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetBalanceActivity extends AppCompatActivity {


    private EditText setBalanceET;
    private Button addBtn;
    private TextView showBalanceTV;
    private String showBalanceTV2;
    private static int Balance=0;

    private int haveValue;
    private DataBaseSource dataBaseSource;
    private MyExpenseDatabase myExpenseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_balance);
        setBalanceET= (EditText) findViewById(R.id.writeBalance);
        showBalanceTV= (TextView) findViewById(R.id.setbudget);

        dataBaseSource = new DataBaseSource(this);

        if(dataBaseSource.test()) {
            haveValue = dataBaseSource.getBalance(1);
            setBalanceET.setText(Integer.toString(haveValue));
        }
        else setBalanceET.setText("0");


    }

    public void addBalance(View view) {
        Balance = Integer.parseInt(String.valueOf(setBalanceET.getText()));
        if(dataBaseSource.test()) {
            dataBaseSource.updateBalance(1,Balance);
        }
        else dataBaseSource.addBalance(Balance);
        Toast.makeText(SetBalanceActivity.this,"Added Successfully",Toast.LENGTH_SHORT).show();
        finish();

    }


}
