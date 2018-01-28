package com.example.nhvn.dogswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private byte num = 0;
    public void switchDog(View view){
        ImageView dogImageView = findViewById(R.id.dog);
        if(num == 0){
            dogImageView.setImageResource(R.drawable.dog1);
            num = 1;
        } else {
            dogImageView.setImageResource(R.drawable.dog2);
            num = 0;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
