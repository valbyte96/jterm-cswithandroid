package com.scarne.valbyte96.scarne;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ScarneActivity extends AppCompatActivity {
    private int totalUserScore;
    private int turnUserScore;
    private int totalCompScore;
    private int turnCompScore;

    // Create a new Handler. This schedules runnables.
    final Handler mHandler = new Handler();

    // Create a new Runnable. This executes some code when run by the handler.
    final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            textView.setText("Next number: " + mRandom.nextInt(100));
            mHandler.postDelayed(this, 1000);
        }
    };
    mHandler.postDelayed(mRunnable, 1000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarne);
    }


    public void computerTurn(){
        while(true){

        }

    }

    public void holdDice(View view){
        TextView scoreView = (TextView) findViewById(R.id.text);
        totalUserScore+=turnUserScore;
        String finalScore = "Your score: "+totalUserScore+" Computer score: "+totalCompScore;
        scoreView.setText(finalScore);
    }


    //helper method; returns random number between 1 and 6
    public int randDice(){
        Random rand = new Random();
        int randNum = rand.nextInt((6 - 1) + 1) + 1;
        return randNum;

    }


    public void rollDice(View view){
        int randNum = randDice();
        ImageView imageView = (ImageView) findViewById(R.id.dice);
        TextView scoreView = (TextView) findViewById(R.id.text);

        if (randNum==1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            turnUserScore =0;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);
        }
        else if(randNum==2){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
            turnUserScore +=2;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);;
        }
        else if(randNum==3){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
            turnUserScore+=3;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);
        }
        else if(randNum==4){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
            turnUserScore+=4;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);
        }
        else if(randNum==5){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
            turnUserScore+=5;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);
        }
        else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
            turnUserScore+=6;
            int shownScore = turnUserScore+totalUserScore;
            String newScore = "Your score: "+shownScore+" Computer score: "+totalCompScore;
            scoreView.setText(newScore);

        }


    }
}
