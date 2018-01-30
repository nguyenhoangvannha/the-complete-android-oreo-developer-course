package com.example.nhvn.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                StringBuilder result = new StringBuilder("");
                int data = reader.read();
                while (data != -1){
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }
                return  result.toString();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo =jsonObject.getString("weather");
                Log.i("Weather content", weatherInfo);

                JSONArray jsonArray = new JSONArray(weatherInfo);
                String message = "";
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    message = jsonPart.getString("main") + "\n" + jsonPart.getString("description");
                }
                if(!message.equals("")){
                    textView = findViewById(R.id.textView);
                    textView.setText(message);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void getWeather(View view){
        EditText editText = findViewById(R.id.editText);
        String city = editText.getText().toString();

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://openweathermap.org/data/2.5/weather?q="+ city+"&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
