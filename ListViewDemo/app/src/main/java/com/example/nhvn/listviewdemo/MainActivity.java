package com.example.nhvn.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.myListView);
        ArrayList<String> family = new ArrayList<String>();
        family.add("Nguyen Vinh");
        family.add("Mai Thi Hoa");
        family.add("Nguyen Van Nhan");
        family.add("Nguyen Thi Huong Thao");
        family.add("Nguyen Hoang Van Nha");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, family);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Member", arrayAdapter.getItem(i));
                showToast(i,arrayAdapter);
            }
        });
    }

    private void showToast(int i, ArrayAdapter<String> arrayAdapter) {
        Toast.makeText(this, arrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
    }

}
