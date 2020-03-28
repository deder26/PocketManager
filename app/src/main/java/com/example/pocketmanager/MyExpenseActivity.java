package com.example.pocketmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyExpenseActivity extends AppCompatActivity {

    private ListView ExpenseListView;
    //private ExpenseDetails ExDetails;
    private ArrayList<ExpenseDetails> ExDetailList;
    private ExpenseListAdapter ExAdapter;
    private DataBaseSource dataBaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expense);
        ExpenseListView = findViewById(R.id.myExpenseList);
       // ExDetails = new ExpenseDetails();
        dataBaseSource = new DataBaseSource(this);
        ExDetailList = dataBaseSource.getAllExpenseDetails();

        ExAdapter = new ExpenseListAdapter(this,ExDetailList);

        ExpenseListView.setAdapter(ExAdapter);

        ExpenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int id =  i;
                AlertDialog.Builder alert = new AlertDialog.Builder(MyExpenseActivity.this);

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int rowId = ExDetailList.get(id).getId();
                        String detail = ExDetailList.get(id).getExpenseDetails();
                        String cost = ExDetailList.get(id).getCost();
                        String dateTime = ExDetailList.get(id).getDateTime();

                        Intent updateIntent = new Intent(MyExpenseActivity.this,UpdateExpenseActivity.class);
                        updateIntent.putExtra("ID",id+"");
                        updateIntent.putExtra("rowID",rowId+"");
                        updateIntent.putExtra("detail",detail);
                        updateIntent.putExtra("cost",cost);
                        updateIntent.putExtra("dateTime",dateTime);


                        startActivity(updateIntent);

                    }
                });
                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        int rowID = ExDetailList.get(id).getId();
                        int costInt = Integer.parseInt(ExDetailList.get(id).getCost());
                        boolean status = dataBaseSource.deleteExpense(rowID);
                        if(status) {
                            Toast.makeText(MyExpenseActivity.this, "Successfully Deleted"+"/n"+"Product cost is added to Balance", Toast.LENGTH_SHORT).show();
                            int balance = dataBaseSource.getBalance(1);
                            balance = balance + costInt;
                            dataBaseSource.updateBalance(1,balance);



                        }
                        else Toast.makeText(MyExpenseActivity.this,"Please Try Again",Toast.LENGTH_SHORT).show();



                        finish();
                    }
                });

                alert.show();

            }
        });


    }

    public void addExpense(View view) {
        Intent intent = new Intent(MyExpenseActivity.this,AddExpense.class);
        startActivity(intent);
    }
}
