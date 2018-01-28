package com.example.nhvn.connect3game;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    GridLayout grid;
    TextView textView;
    Button button;
    int counter;
    byte player; // 0:Red 1:Yellow
    int pos[][] = new int[7][7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        grid = findViewById(R.id.gridLayout);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);
        resetGame();
    }
    public void resetGame(){
        player = 0;
        counter = 0;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                pos[i][j] = -1;
            }
        }
        button.setAlpha(0);
        textView.setText("Playing...");
        for(int i = 0; i < grid.getChildCount(); i++){
            ImageView imageView = (ImageView) grid.getChildAt(i);
            imageView.setImageDrawable(null);
        }
    }
    public void playGame(View view){
        switch (view.getId()){
            case R.id.imageView:
                fade(img1, player, 2, 2);
                if(checkWin(2,2)) whenWin();
                break;
            case R.id.imageView2:
                fade(img2, player, 2,3 );
                if(checkWin(2,3)) whenWin();
                break;
            case R.id.imageView3:
                fade(img3, player,2 ,4 );
                if(checkWin(2,4)) whenWin();
                break;
            case R.id.imageView4:
                fade(img4, player,3 ,2 );
                if(checkWin(3,2)) whenWin();
                break;
            case R.id.imageView5:
                fade(img5, player,3 ,3 );
                if(checkWin(3,3)) whenWin();
                break;
            case R.id.imageView6:
                fade(img6, player,3 ,4 );
                if(checkWin(3,4)) whenWin();
                break;
            case R.id.imageView7:
                fade(img7, player,4 ,2 );
                if(checkWin(4,2)) whenWin();
                break;
            case R.id.imageView8:
                fade(img8, player,4 ,3 );
                if(checkWin(4,3)) whenWin();
                break;
            case R.id.imageView9:
                fade(img9, player,4 ,4 );
                if(checkWin(4,4)) whenWin();
                break;
        }
    }
    private void whenWin(){
        String message = "";
        if(player == 0){
            message = "Yellow win!";
        } else {
            message = "Red win!";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        textView.setText("The " + message);
        button.setAlpha(1);
    }
    private boolean checkWin(int x, int y){
        if(pos[x][y] == -1) return false;
        if(pos[x - 1][y] == pos[x][y] && pos[x+1][y] == pos[x][y]){
            return true;
        }
        if(pos[x][y - 1] == pos[x][y] && pos[x][y + 1] == pos[x][y]){
            return true;
        }
        if(pos[x - 1][y - 1] == pos[x][y] && pos[x+1][y + 1] == pos[x][y]){
            return true;
        }
        if(pos[x + 1][y - 1] == pos[x][y] && pos[x-1][y+1] == pos[x][y]){
            return true;
        }
        if(pos[x][y+1] == pos[x][y] && pos[x][y+2] == pos[x][y]){
            return true;
        }
        if(pos[x][y-1] == pos[x][y] && pos[x][y-2] == pos[x][y]){
            return true;
        }
        if(pos[x - 1][y] == pos[x][y] && pos[x - 2][y] == pos[x][y]){
            return true;
        }
        if(pos[x + 2][y] == pos[x][y] && pos[x + 2][y] == pos[x][y]){
            return true;
        }
        if(pos[x + 1][y + 1] == pos[x][y] && pos[x + 2][y + 2] == pos[x][y]){
            return true;
        }
        if(pos[x - 1][y - 1] == pos[x][y] && pos[x - 2][y - 2] == pos[x][y]){
            return true;
        }
        if(pos[x + 1][y - 1] == pos[x][y] && pos[x + 2][y - 2] == pos[x][y]){
            return true;
        }
        if(pos[x - 1][y + 1] == pos[x][y] && pos[x - 2][y + 2] == pos[x][y]){
            return true;
        }
        if(counter == 9){
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private void fade(View view, byte player, int x, int y){
        if(pos[x][y] != -1){
            Toast.makeText(this, "Already choose" , Toast.LENGTH_SHORT).show();
            return;
        }
        ImageView v = (ImageView) view;
        counter++;
        pos[x][y] = this.player;
        Log.i("Player", pos[x][y] + "");
        if(player == 0){
            v.setImageResource(R.drawable.red);
            this.player = 1;
        } else {
            v.setImageResource(R.drawable.yellow);
            this.player = 0;
        }
        v.setY(v.getY()-100);
        v.animate().translationYBy(100).alpha(1).setDuration(500);
    }
    public void playAgain(View view){
        resetGame();
    }
}
