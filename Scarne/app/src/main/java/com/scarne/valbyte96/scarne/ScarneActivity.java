package com.scarne.valbyte96.scarne;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ScarneActivity extends AppCompatActivity {
    private int totalUserScore;
    private int turnUserScore;
    private int totalCompScore;
    private int turnCompScore;
    private int numCompRounds;
    private boolean userTurn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarne);
    }


    final Handler mHandler = new Handler();

    final TextView scoreView = (TextView) findViewById(R.id.text);
    final ImageView imageView = (ImageView) findViewById(R.id.dice);


    // Create a new Runnable. This executes some code when run by the handler.
    final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(!userTurn & numCompRounds<10){
                Button rollButton = (Button) findViewById(R.id.button1);
                rollButton.setClickable(false);
                Button holdButton = (Button) findViewById(R.id.button2);
                holdButton.setClickable(false);
                computerTurn();
                updateScore();
                mHandler.postDelayed(this, 1000);

            }
        }
    };

    public void updateScore(){
        int shownUserScore = turnUserScore+totalUserScore;
        int shownCompScore = turnCompScore+totalCompScore;
        String newScore = "Your score: "+shownUserScore+" Computer score: "+shownCompScore;
        scoreView.setText(newScore);
    }

    public void computerTurn(){
            int randNum = randDice();
            if (randNum==1){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                turnCompScore =0;
            }
            else if(randNum==2){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                turnCompScore +=2;
            }
            else if(randNum==3){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                turnCompScore +=3;
            }
            else if(randNum==4){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                turnCompScore +=4;
            }
            else if(randNum==5){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                turnCompScore +=5;
            }
            else{
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                turnCompScore +=6;
            }
            numCompRounds+=1;
    }

    public void holdDice(){
        TextView scoreView = (TextView) findViewById(R.id.text);
        totalUserScore+=turnUserScore;
        String finalScore = "Your score: "+totalUserScore+" Computer score: "+totalCompScore;
        scoreView.setText(finalScore);
        computerTurn();

    }


    //helper method; returns random number between 1 and 6
    public int randDice(){
        Random rand = new Random();
        int randNum = rand.nextInt((6 - 1) + 1) + 1;
        return randNum;

    }


    public void rollDice(){
        int randNum = randDice();
        ImageView imageView = (ImageView) findViewById(R.id.dice);
//        TextView scoreView = (TextView) findViewById(R.id.text);

        if (randNum==1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            turnUserScore =0;
            userTurn=false;

        }
        else if(randNum==2){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
            turnUserScore +=2;

        }
        else if(randNum==3){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
            turnUserScore+=3;
        }
        else if(randNum==4){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
            turnUserScore+=4;
        }
        else if(randNum==5){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
            turnUserScore+=5;
        }
        else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
            turnUserScore+=6;
        }

    }


    public void reset(){
        TextView scoreView = (TextView) findViewById(R.id.text);
        String newScore = "Your score: "+0+" Computer score: "+0;
        scoreView.setText(newScore);

    }
}
