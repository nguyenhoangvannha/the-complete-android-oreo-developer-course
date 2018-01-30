package com.example.nhvn.tinhte;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nhvn.tinhte.adapter.TinhTePostAdapter;
import com.example.nhvn.tinhte.model.TinhTePost;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ListView newFeedListView;
    ArrayList<TinhTePost> tinhTePosts;
    TinhTePostAdapter tinhTePostAdapter;

    public class DownloadNewFeedTask extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            ArrayList<String> posts = new ArrayList<String>();
            URL url = null;
            try {
                url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                StringBuilder webPage = new StringBuilder("");
                int data = reader.read();
                while (data != -1){
                    char current = (char) data;
                    webPage.append(current);
                    data = reader.read();
                }
                Pattern p = Pattern.compile("data-author=(.*?) class=\"post-body", Pattern.DOTALL);
                Matcher m = p.matcher(webPage.toString());

                while(m.find()) {
                    posts.add(m.group(1));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return posts;
        }
    }

    public class DownloadPostTask extends AsyncTask<String, Void, TinhTePost>{

        @Override
        protected TinhTePost doInBackground(String... contents) {
            try {
                //Pattern p = Pattern.compile("\"(.*?)\".*img src=\"(.*?)\" width.*(threads?)(.*?)\" class", Pattern.DOTALL);
                Pattern p = Pattern.compile("\"(.*?)\".*img src=\"(.*?)\" width.*(threads?)(.*?)\" class.*(\">?)(.*?)</a>", Pattern.DOTALL);
                Matcher m = p.matcher(contents[0]);
                while (m.find()){
                    Log.i("AVATAR", m.group(1) + " | " + m.group(2) + " | " + m.group(4) + " | " +  m.group(6));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void loadNewFeed(){
        DownloadNewFeedTask task = new DownloadNewFeedTask();
        try {
            ArrayList<String> partContents = task.execute("https://tinhte.vn/").get();
            for(String content:partContents){
                DownloadPostTask downloadPostTask = new DownloadPostTask();
                TinhTePost tinhTePost = downloadPostTask.execute(content).get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }
        initComponents();
        loadNewFeed();
    }

    private void initComponents() {
        newFeedListView = findViewById(R.id.newFeedListView);
        tinhTePosts = new ArrayList<TinhTePost>();
        tinhTePosts.add(new TinhTePost("", null, null, "nhanguyen", "16:12 chiều", "Trai dat", "Xin chao"));
        tinhTePostAdapter = new TinhTePostAdapter(tinhTePosts, this);
        newFeedListView.setAdapter(tinhTePostAdapter);
    }
}
