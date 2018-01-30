package com.example.nhvn.guessthecelebrity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<Celebrity> celebrities;
    Button[] buttons;
    ImageView imageView;
    int correctPos;

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (Exception e) {
                return null;
            }
        }
    }

    private class WebContentDownloader extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int current = reader.read();
                StringBuilder result = new StringBuilder("");
                while (current != -1){
                    char c = (char) current;
                    result.append(c);
                    current = reader.read();
                }
                return result.toString();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public void checkAnswer(View view){
        int answerPos = -1;
        switch (view.getId()){
            case R.id.button:
                answerPos = 0;
                break;
            case R.id.button2:
                answerPos = 1;
                break;
            case R.id.button3:
                answerPos = 2;
                break;
            case R.id.button4:
                answerPos = 3;
                break;
        }

        if(answerPos == -1 || correctPos == -1){
            Toast.makeText(this, "Cannot load question", Toast.LENGTH_SHORT).show();
        } else {
            if(answerPos != correctPos){
                Toast.makeText(this, "Incorrect T_T", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Correct *-*", Toast.LENGTH_SHORT).show();
            }
        }
        correctPos = loadQuestion();
    }

    private int loadQuestion(){
        Random random = new Random();
        int celebrityPos = random.nextInt(celebrities.size());
        Celebrity celebrity = celebrities.get(celebrityPos);
        try {
            Bitmap avatar = new ImageDownloader().execute(celebrity.getUrl()).get();
            imageView.setImageBitmap(avatar);
        } catch (InterruptedException e) {
            return -1;
        } catch (ExecutionException e) {
            return -1;
        }

        String op1 = celebrities.get(random.nextInt(celebrities.size())).getName();
        String op2 = celebrities.get(random.nextInt(celebrities.size())).getName();
        String op3 = celebrities.get(random.nextInt(celebrities.size())).getName();
        String op4 = celebrities.get(random.nextInt(celebrities.size())).getName();
        String[] options = {op1, op2, op3, op4};

        int correctPos = random.nextInt(4);
        buttons[correctPos].setText(celebrity.getName());

        for(int i = 0; i < 4; i++){
            if(i != correctPos){
                buttons[i].setText(options[i]);
            }
        }
        return correctPos;
    }

    private void loadCelebrity(String url) {
        WebContentDownloader downloader = new WebContentDownloader();
        try {
            String result = downloader.execute(url).get();
            if(result == null){
                Toast.makeText(this, "Cannot load celebrity list", Toast.LENGTH_SHORT).show();
            } else {
                Pattern p = Pattern.compile("<img src=\"(.*?)\" alt=\"(.*)\"");
                Matcher m = p.matcher(result);
                while (m.find()){
                    celebrities.add(new Celebrity(m.group(2), m.group(1)));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private void initComponents() {
        imageView = findViewById(R.id.imageView);
        buttons = new Button[4];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);

        correctPos = -1;
        celebrities = new ArrayList<Celebrity>();
        loadCelebrity("http://www.posh24.se/kandisar");
        correctPos = loadQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }

        initComponents();
    }


}
