package com.example.nhvn.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void login(View view){
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Toast.makeText(this, "User:" + nameEditText.getText().toString()
                + "\nPassword:" + passwordEditText.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
