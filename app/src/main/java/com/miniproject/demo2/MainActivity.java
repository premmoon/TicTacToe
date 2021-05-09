package com.miniproject.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int currentPlayer = 0;
    boolean gameOn = true;
    int clickCounter = 0;

    int[] board     = {0,0,0
                      ,0,0,0
                       ,0,0,0};

     int[][] winPositions = {
             {0,1,2},{3,4,5},{6,7,8},
             {0,3,6},{1,4,7},{2,5,8},
             {0,4,8},{2,4,6}
     };

    ImageView pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos0;
    TextView txtWon;
    MediaPlayer xSound ;
    MediaPlayer oSound ;
    MediaPlayer winSound ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pos0 = findViewById(R.id.pos0);
        pos1 = findViewById(R.id.pos1);
        pos2 = findViewById(R.id.pos2);
        pos3 = findViewById(R.id.pos3);
        pos4 = findViewById(R.id.pos4);
        pos5 = findViewById(R.id.pos5);
        pos6 = findViewById(R.id.pos6);
        pos7 = findViewById(R.id.pos7);
        pos8 = findViewById(R.id.pos8);
        txtWon = findViewById(R.id.textView);
        xSound = MediaPlayer.create(this,R.raw.punch1);
        oSound = MediaPlayer.create(this,R.raw.punch2);
        winSound = MediaPlayer.create(this,R.raw.villan);


    }

    public void imageTapped(View view)
    {
        int tag = Integer.parseInt(view.getTag().toString());
        ImageView tappedImage = (ImageView) view;
        if (gameOn) {

            if (currentPlayer == 0 && board[tag] == 0) {
                oSound.start();
                tappedImage.setImageResource(R.drawable.zero);
                txtWon.setText("X's Turn");
                currentPlayer = 1;
                board[tag] = 1;
                clickCounter++;
                checkWinner();
            } else if (currentPlayer == 1 && board[tag] == 0) {
                xSound.start();
                tappedImage.setImageResource(R.drawable.ex);
                txtWon.setText("O's Turn");
                currentPlayer = 0;
                board[tag] = 2;
                clickCounter++;
                checkWinner();
            }

        }
    }


    public void checkWinner(){
        for (int[] winPos :winPositions){
            if (board [winPos[0]] == board[winPos[1]] && board[winPos[1]] == board[winPos[2]]
                    &&board[winPos[0]]!= 0){

                if(board[winPos[0]]==1) {
                    winSound.start();
                    txtWon.setText("0 won click play again !");
                    gameOn = false;
                }
                else if (board[winPos[0]] ==2){
                    winSound.start();
                    txtWon.setText("X won click play again !");
                    gameOn = false;
                }
            }
        }
        if (clickCounter >=9){
            if (gameOn) {
                txtWon.setText("match draw !");

            }
            gameOn = false;
        }


    }


    public void playAgain(View view){

        pos0.setImageResource(0);
        pos1.setImageResource(0);
        pos2.setImageResource(0);
        pos3.setImageResource(0);
        pos4.setImageResource(0);
        pos5.setImageResource(0);
        pos6.setImageResource(0);
        pos7.setImageResource(0);
        pos8.setImageResource(0);
                for (int i = 0 ;i<9;i++){
                    board[i] = 0;

                }
                txtWon.setText("Welcome to Tac Tic Toe");
                currentPlayer = 1;
                gameOn = true ;
                clickCounter = 0 ;

    }
}
