package com.example.nhvn.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    ListView listView;
    ArrayList<Integer> arrs;
    ArrayAdapter<Integer> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        listView = findViewById(R.id.listview);

        arrs = new ArrayList<>();
        createData(arrs, 0);
        arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arrs);
        listView.setAdapter(arrayAdapter);

        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                createData(arrs, i);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void createData( ArrayList<Integer> arrs,int value){
        arrs.clear();
        for(int i = 1; i < 10; i++){
            arrs.add(value * i);
        }
    }
}
