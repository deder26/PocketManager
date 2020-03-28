package com.example.pocketmanager;

import java.util.ArrayList;

public class ExpenseDetails {
    private String DateTime;
    private String ExpenseDetails;
    private String Cost;
    private int id;
    private DataBaseSource dataBaseSource;

    public ExpenseDetails(String dateTime, String expenseDetails, String cost,int id) {
        DateTime = dateTime;
        ExpenseDetails = expenseDetails;
        Cost = cost;
        this.id=id;
    }

    public ExpenseDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public String getExpenseDetails() {
        return ExpenseDetails;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public void setExpenseDetails(String expenseDetails) {
        ExpenseDetails = expenseDetails;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getCost() {
        return Cost;
    }


    public ArrayList<ExpenseDetails> getDetails(){
        ArrayList<ExpenseDetails> ExpenseDeatail = new ArrayList<>();

        ExpenseDeatail = dataBaseSource.getAllExpenseDetails();

        return ExpenseDeatail;
    }


}
