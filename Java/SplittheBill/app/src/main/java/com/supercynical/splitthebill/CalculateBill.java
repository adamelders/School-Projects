package com.supercynical.splitthebill;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculateBill extends AppCompatActivity {

    double billAmount = 0.0;
    int numberInParty = 1;
    double tipPercent = 0.18;
    double tipAmount = 0.0;
    double totalBillAmount = 0.0;
    double amountPerPerson = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bill);

        final EditText billAmountText = (EditText)findViewById(R.id.totalBillText);
        final EditText numberInPartyText = (EditText)findViewById(R.id.numberInPartyText);
        final TextView tipAmountValue = (TextView)findViewById(R.id.tipAmount);
        final TextView paymentAmountValue = (TextView)findViewById(R.id.paymentAmount);
        final TextView qualityValue = (TextView)findViewById(R.id.qualityText);
        final Spinner group = (Spinner)findViewById(R.id.spinner);
        final DecimalFormat df = new DecimalFormat("$#.00");

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.quality_array,
                android.R.layout.simple_spinner_item);
        group.setAdapter(adapter);

        Button calculate = (Button)findViewById(R.id.calculateButton);
        calculate.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                // Get values
                billAmount = Double.parseDouble(billAmountText.getText().toString());
                try {
                    numberInParty = Integer.parseInt(numberInPartyText.getText().toString());
                } catch (NumberFormatException ex) {
                    Toast.makeText(CalculateBill.this,
                            R.string.intNumberInPartyException,
                            Toast.LENGTH_SHORT).show();
                }

                // Calculate values
                tipAmount = billAmount * tipPercent;
                totalBillAmount = billAmount + tipAmount;
                amountPerPerson = totalBillAmount / numberInParty;

                // Set values to TextViews
                tipAmountValue.setText(df.format(tipAmount));
                paymentAmountValue.setText(df.format(amountPerPerson));
                qualityValue.setText(group.getSelectedItem().toString());
            }
        });
    }
}
