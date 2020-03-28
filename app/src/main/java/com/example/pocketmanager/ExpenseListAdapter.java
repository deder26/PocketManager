package com.example.pocketmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ExpenseListAdapter extends ArrayAdapter<ExpenseDetails> {

    private Context context;
    private ArrayList<ExpenseDetails> ExDetails;

    public ExpenseListAdapter(@NonNull Context context, ArrayList<ExpenseDetails>ExDeatails) {
        super(context, R.layout.list_custom_view_layout, ExDeatails);

        this.context = context;
        this.ExDetails = ExDeatails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_custom_view_layout,parent,false);

        TextView DateTimeTV = convertView.findViewById(R.id.listTime);
        TextView DetailsTV = convertView.findViewById(R.id.listDetails);
        TextView CostTV = convertView.findViewById(R.id.listTk);

        DateTimeTV.setText(ExDetails.get(position).getDateTime());
        DetailsTV.setText(ExDetails.get(position).getExpenseDetails());
        CostTV.setText(ExDetails.get(position).getCost()+"/-");

        return convertView;

    }
}
