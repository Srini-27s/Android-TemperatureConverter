package com.example.tempconvertor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

        private EditText editTextTemperature;
        private Spinner spinnerConversionType;
        private Button buttonConvert;
        private TextView textViewResult;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                editTextTemperature = findViewById(R.id.editTextTemperature);
                spinnerConversionType = findViewById(R.id.spinnerConversionType);
                buttonConvert = findViewById(R.id.buttonConvert);
                textViewResult = findViewById(R.id.textViewResult);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        this, R.array.conversion_types, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerConversionType.setAdapter(adapter);

                buttonConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                convertTemperature();
                        }
                });

                spinnerConversionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Clear the result text when the conversion type is changed
                                textViewResult.setText("");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                                // Do nothing here
                        }
                });
        }

        private void convertTemperature() {
                try {
                        double temperature = Double.parseDouble(editTextTemperature.getText().toString());
                        String selectedConversion = spinnerConversionType.getSelectedItem().toString();

                        double result;
                        if (selectedConversion.equals("Celsius to Fahrenheit")) {
                                result = (temperature * 9/5) + 32;
                        } else if (selectedConversion.equals("Fahrenheit to Celsius")) {
                                result = (temperature - 32) * 5/9;
                        } else {
                                result = 0; // Default to 0 if an invalid conversion is selected
                        }

                        textViewResult.setText(String.format("%.2f", result));
                } catch (NumberFormatException e) {
                        textViewResult.setText("Invalid input");
                }
        }
}
