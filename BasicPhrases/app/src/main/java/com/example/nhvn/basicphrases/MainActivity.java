package com.example.nhvn.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void playPhrases(View view){
        Button buttonPressed = (Button) view;
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier((String) buttonPressed.getTag(), "raw", getPackageName()));
        mediaPlayer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
