package com.example.pocketmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button setBalanceBtn;
    private Button myExpenseBtn;
    private TextView remainBalanceTV;
    private int haveValue;
    private boolean check;
    private DataBaseSource dataBaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remainBalanceTV =(TextView) findViewById(R.id.remainBalance);
        setBalanceBtn =(Button) findViewById(R.id.setBalance);
        myExpenseBtn =(Button) findViewById(R.id.myExpense);
        dataBaseSource = new DataBaseSource(this);

        check = dataBaseSource.test();
        if(check) {
           haveValue = dataBaseSource.getBalance(1);
           remainBalanceTV.setText(Integer.toString(haveValue));
        }
        else remainBalanceTV.setText("0");

    }

    public void setBalance(View view) {
        Intent intentSetBalance = new Intent(MainActivity.this,SetBalanceActivity.class);

        startActivity(intentSetBalance);
    }

    public void myExpense(View view) {
        Intent intentMyExpense = new Intent(MainActivity.this,MyExpenseActivity.class);
        //String strRemainBalance = remainBalanceTV.getText().toString();
        //intentMyExpense.putExtra("remainBalance",strRemainBalance);
        //startActivityForResult(intentMyExpense,2);
        startActivity(intentMyExpense);

    }

}
