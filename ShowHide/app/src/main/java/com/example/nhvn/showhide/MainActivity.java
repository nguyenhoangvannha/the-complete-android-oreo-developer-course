package com.example.nhvn.showhide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
    public void myFunction(View view){
        switch (view.getId()){
            case R.id.show_button:
                textView.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_button:
                textView.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
