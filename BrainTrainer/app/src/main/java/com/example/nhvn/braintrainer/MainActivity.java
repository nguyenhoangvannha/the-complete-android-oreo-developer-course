package com.example.nhvn.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, scoreTextView, questionTextView, gameStateTextView;
    Button startButton, playButton, button, button2, button3, button4;
    int correctAnswer;
    int correctCount;
    int questionCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        gameStateTextView = findViewById(R.id.gameStateTextView);
        playButton = findViewById(R.id.playButton);
        startButton = findViewById(R.id.startButton);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);


        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        gameStateTextView.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);

    }

    private int generateQuestions() {
        Random r = new Random();
        int a = r.nextInt(100);
        int b = r.nextInt(100);
        int questionType = r.nextInt(4); // 0: +     1: -      2: *    3:   /
        int result = 0;
        char questionTypeChar = ' ';
        switch (questionType){
            case 0:
                result = a + b;
                questionTypeChar = '+';
                break;
            case 1:
                result = a - b;
                questionTypeChar = '-';
                break;
            case 2:
                result = a * b;
                questionTypeChar = '*';
                break;
            case 3:
                result = a / b;
                questionTypeChar = ':';
                break;
        }
        int op1 = r.nextInt(500);
        int op2 = r.nextInt(500);
        int op3 = r.nextInt(1000);

        questionTextView.setText(a + " " + questionTypeChar + " " + b );
        button.setText(Integer.toString(op1));
        button2.setText(Integer.toString(op2));
        button3.setText(Integer.toString(op3));
        button4.setText(Integer.toString(result));
        Log.i("Question", a + " | " + b  + " | "+ result + " thanh " + op1 + " | "+op2 +" | "+ op3 +" | "+ " type " + questionTypeChar);
        return result;
    }

    public void checkWin(View view){
        String result = "";
        switch (view.getId()){
            case R.id.button:
                result = button.getText().toString();
                break;
            case R.id.button2:
                result = button2.getText().toString();
                break;
            case R.id.button3:
                result = button3.getText().toString();
                break;
            case R.id.button4:
                result = button4.getText().toString();
                break;
        }
        if(result.equals(Integer.toString(correctAnswer))){
            correctCount++;
            gameStateTextView.setText("Correct :v");
        } else {
            gameStateTextView.setText("Incorrect T_T");
        }
        questionCount++;
        scoreTextView.setText(correctCount + "/" + questionCount);
        correctAnswer = generateQuestions();
    }

    public void startButtonOnClicked(View view){
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        gameStateTextView.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
    }
    public void playGame(View view){
        resetGame();
        correctAnswer = generateQuestions();
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                updateTimerTextView(l);
            }

            @Override
            public void onFinish() {
                playButton.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();
    }
    public void updateTimerTextView(long timeLeft){
        timerTextView.setText(timeLeft / 1000 + "s");
    }
    private void resetGame() {
        playButton.setVisibility(View.INVISIBLE);
        button.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        correctCount = 0;
        questionCount = 0;
    }
}
