package com.laurennguyen.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laurennguyen.tipcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Logging
        Log.d("Debugging", "Successful loading");

        //Make a toast message
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.welcome_toast),Toast.LENGTH_SHORT).show();

        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double tip = calculateTip();
                binding.textviewTipAmount.setText(getResources().getString(R.string.dollar_sign) + String.format("%.2f", tip));
            }
        });

        //Implement Calculate button
        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double tip = calculateTip();
                double total = calculateTotal();
                //formatting strings
                binding.textviewTipAmount.setText(getResources().getString(R.string.dollar_sign) + String.format("%.2f",tip));
                binding.textviewTotal.setText(getResources().getString(R.string.dollar_sign) + String.format("%.2f",total));
            }
        });

        //Implement Reset button
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //clearing all EditTexts
                binding.edittextBill.setText(getResources().getString(R.string.empty_string));
                binding.edittextTipPercent.setText(getResources().getString(R.string.empty_string));
                binding.textviewTipAmount.setText(getResources().getString(R.string.default_double));
                binding.textviewTotal.setText(getResources().getString(R.string.default_double));
            }
        });
    }

    public double calculateTip() {
        String billField = binding.edittextBill.getText().toString();
        String tipField = binding.edittextTipPercent.getText().toString();
        if (billField.isEmpty() || tipField.isEmpty()) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_fields), Toast.LENGTH_LONG).show();
            return 0.0;
        }
        double bill = Double.valueOf(billField);
        double tip_percent = Double.valueOf(tipField);
        double tip = bill*tip_percent/100;
        return tip;
    }

    public double calculateTotal(){
        String billField = binding.edittextBill.getText().toString();
        if (billField.isEmpty()) {
            return 0.0;
        }
        double bill = Double.valueOf(billField);
        double tip = calculateTip();
        double total = bill + tip;
        return total;
    }
}