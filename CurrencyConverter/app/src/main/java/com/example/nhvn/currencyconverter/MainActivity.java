package com.example.nhvn.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){
        EditText amount = findViewById(R.id.editText);
        float result = (float) (1.4 * Float.parseFloat(amount.getText().toString()));
        Toast.makeText(this, amount.getText().toString() + " Pound = " +result + " USD", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
