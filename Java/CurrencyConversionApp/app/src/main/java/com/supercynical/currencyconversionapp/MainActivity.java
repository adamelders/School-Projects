package com.supercynical.currencyconversionapp;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double baseAmount = 0.0;
    double euroRate = 0.90581;
    double pesoRate = 18.1644;
    double canadaRate = 1.37373;
    double convertedAmount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText baseAmountText = (EditText)findViewById(R.id.baseAmount);
        final DecimalFormat df = new DecimalFormat("$#.00");
        final RadioButton usToEuro = (RadioButton)findViewById(R.id.euroRadioButton);
        final RadioButton usToPeso = (RadioButton)findViewById(R.id.pesosRadioButton);
        final RadioButton usToCanada = (RadioButton)findViewById(R.id.canadaRadioButton);
        final TextView convertedAmountText = (TextView)findViewById(R.id.convertedAmount);

        Button button = (Button)findViewById(R.id.convertButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseAmount = Double.parseDouble(baseAmountText.getText().toString());

                if (baseAmount < 100000.0)
                {
                    if (usToEuro.isChecked())
                    {
                        convertedAmount = baseAmount * euroRate;
                    }
                    else if (usToPeso.isChecked())
                    {
                        convertedAmount = baseAmount * pesoRate;
                    }
                    else if (usToCanada.isChecked())
                    {
                        convertedAmount = baseAmount * canadaRate;
                    }
                    convertedAmountText.setText(df.format(convertedAmount));
                }
                else
                {
                    Toast.makeText(MainActivity.this, R.string.amountFailText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
