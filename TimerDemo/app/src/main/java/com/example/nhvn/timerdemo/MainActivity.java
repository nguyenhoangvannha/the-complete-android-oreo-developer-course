package com.example.nhvn.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long l) {
                Log.i("Second left", l/1000 + "");
            }

            @Override
            public void onFinish() {

            }
        };

//        final Handler handler = new Handler();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Say hi"," A second passed by");
//                handler.postDelayed(this, 1000);
//            }
//        };
    }
}
