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
//        if(switcher == true){
//            imageView.animate().alpha(0).setDuration(2000);
//            imageView2.animate().alpha(1).setDuration(2000);
//            switcher = false;
//        } else {
//            imageView.animate().alpha(1).setDuration(2000);
//            imageView2.animate().alpha(0).setDuration(2000);
//            switcher = true;
//        }
        //imageView.animate().translationYBy(1000).setDuration(2000);
        //imageView.animate().translationXBy(-1000).setDuration(2000);
        imageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startApp();
    }

    private void startApp() {
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
        imageView.setX(-500);
        imageView.animate().translationXBy(500).alpha(1).scaleX(1).scaleY(1).setDuration(10000);
    }
}
