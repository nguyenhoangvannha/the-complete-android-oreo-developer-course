package com.example.nhvn.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean switcher = true;
    public void fade(View view){
        Log.i("Info", "ImageView tapped!");
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        if(switcher == true){
            imageView.animate().alpha(0).setDuration(2000);
            imageView2.animate().alpha(1).setDuration(2000);
            switcher = false;
        } else {
            imageView.animate().alpha(1).setDuration(2000);
            imageView2.animate().alpha(0).setDuration(2000);
            switcher = true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
