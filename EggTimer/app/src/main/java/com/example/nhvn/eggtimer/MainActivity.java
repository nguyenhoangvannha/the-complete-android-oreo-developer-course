package com.example.nhvn.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;
    boolean isRuning;
    int remainTime;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        isRuning = false;
        remainTime = 30;
        updateTextView(remainTime);
        seekBar.setMax(10*60);
        seekBar.setProgress(remainTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                remainTime = i;
                updateTextView(remainTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private void updateTextView(int remainTime){
        int minutes = remainTime / 60;
        int seconds = remainTime % 60;
        textView.setText(minutes + ":" + seconds);
    }
    public void runApp(View view){
        remainTime = seekBar.getProgress();
        if(isRuning){
            button.setText("Go");
            updateTextView(remainTime);
            isRuning = false;
            countDownTimer.cancel();
        } else {
            button.setText("Stop");
            countDownTimer = new CountDownTimer(remainTime * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    remainTime -= 1;
                    updateTextView(remainTime);
                    seekBar.setProgress(remainTime);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                    mediaPlayer.start();
                    button.setText("Go");
                    updateTextView(0);
                    isRuning = false;
                }
            };
            countDownTimer.start();
            isRuning = true;
        }
    }
}
