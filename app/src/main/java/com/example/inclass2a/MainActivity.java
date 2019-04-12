package com.example.inclass2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    EditText heightinfeet;
    EditText heightininches;
    EditText weight;
    TextView result, inter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add title
        setTitle("BMI Calculator");
        heightinfeet = (EditText) findViewById(R.id.editTextHeightInFeet);
        heightininches = (EditText) findViewById(R.id.editTextHeightInInches);
        weight = (EditText) findViewById(R.id.editTextWeight);
        result = (TextView) findViewById(R.id.textViewResult);
        btn = findViewById(R.id.button);
        inter = findViewById(R.id.textViewInterpretation);




        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", "inside onCLick");

                String heightinfeetstr = heightinfeet.getText().toString();
                String heightininchesstr = heightininches.getText().toString();
                String weightstr = weight.getText().toString();

                if(TextUtils.isEmpty(heightinfeetstr)){
                    heightinfeet.setError("Please enter your height");
                    heightinfeet.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(heightininchesstr)){
                    heightininches.setError("Please enter your height");
                    heightininches.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(weightstr)){
                    weight.setError("Please enter your weight");
                    weight.requestFocus();
                    return;
                }
                if(Float.parseFloat(weightstr) <= 0 || Float.parseFloat(heightinfeetstr) <= 0 || Float.parseFloat(heightininchesstr) <= 0){
                    Toast.makeText(MainActivity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                }
                else if(Float.parseFloat(weightstr) > 0 && Float.parseFloat(heightinfeetstr) > 0 && Float.parseFloat(heightininchesstr)>0){
                    float weightValue = Float.parseFloat(weightstr);
                    float heightinfeetValue = Float.parseFloat(heightinfeetstr);
                    Log.d("demo", "heightinfeetValue = " + heightinfeetValue);
                    float heightininchesValue = Float.parseFloat(heightininchesstr);
                    Log.d("demo", "heightininchesValue = " + heightininchesValue);
                    float heightValue = heightinfeetValue*12 + heightininchesValue;
                    Log.d("demo", "heightValue = " + heightValue);
                    float bmiValue = calculateBMI(weightValue, heightValue);
//                Log.d("demo" ,"hello");
                    Log.d("demo", "bmiValue = " + bmiValue);
                    String bmiInterpretation = interpretBMI(bmiValue);

                    String s1 = String.format("%.1f", bmiValue);

                    result.setText(String.valueOf(s1));
                    inter.setText(bmiInterpretation);
                    Toast.makeText(MainActivity.this, "BMI Calculated", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private float calculateBMI(float weight, float height)
    {
//        Toast.makeText(this, "calculateBMI = " + weight / (height * height), Toast.LENGTH_SHORT).show();
        return (float) 703 * (weight / (height * height));
    }


    private String interpretBMI(float bmiValue)
    {

        if (bmiValue < 18.5)
        {
            return "Underweight";
        }
        else if (18.5 <= bmiValue  && bmiValue< 25)
        {
            return "Normal";
        }
        else if (bmiValue>25 && bmiValue < 30)
        {
            return "Overweight";
        }
        else if(bmiValue>30)
        {
            return "Obese";
        }
        else
            return "Enter correct input value";
    }





}

