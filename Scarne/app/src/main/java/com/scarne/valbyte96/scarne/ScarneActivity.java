package com.scarne.valbyte96.scarne;

import android.content.Intent;
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
    private int totalCompScore;
    private int numCompRounds;
    private int turnScore;
    private boolean userTurn=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarne);
    }
    public static final String USER_SCORE = "com.valbyte96.scarne.USER_SCORE";
    private void playerWins(){
        Intent intent = new Intent(this,WinActivity.class);
        intent.putExtra(USER_SCORE, String.valueOf(totalUserScore+turnScore));
        startActivity(intent);
        resetScore();
    }

    public void computerWins(){
        startActivity(new Intent(this,LoseActivity.class));
        resetScore();
    }

    final Handler mHandler = new Handler();

    // Create a new Runnable. This executes some code when run by the handler.
    final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            rollDiceComp();
            updateText();
            if (checkScore() == false) {
                if (userTurn == false & numCompRounds < 3) {
                    Button rollButton = (Button) findViewById(R.id.button1);
                    rollButton.setClickable(false);
                    Button holdButton = (Button) findViewById(R.id.button2);
                    holdButton.setClickable(false);
                    computerTurn();


                } else {
                    Button rollButton = (Button) findViewById(R.id.button1);
                    rollButton.setClickable(true);
                    Button holdButton = (Button) findViewById(R.id.button2);
                    holdButton.setClickable(true);
                    totalCompScore += turnScore;
                    turnScore = 0;
                    numCompRounds = 0;
                    userTurn = true;
                }
            }
        }
    };



    public void computerTurn() {
        numCompRounds+=1;
        mHandler.postDelayed(mRunnable, 1000);
    }

    public void holdDice(View view){
        TextView scoreView = (TextView) findViewById(R.id.text);
        totalUserScore+=turnScore;
        String finalScore = "Your score: "+totalUserScore+" Computer score: "+totalCompScore;
        scoreView.setText(finalScore);
        userTurn=false;
        turnScore=0;
        computerTurn();
    }


    //helper method; returns random number between 1 and 6
    public int randDice(){
        Random rand = new Random();
        int randNum = rand.nextInt(6) + 1;
        return randNum;

    }


    public void rollDiceComp(){
        int randNum = randDice();
        if (randNum==1){
            turnScore =0;
            updateText();
            userTurn=true;
            numCompRounds=0;


        }
        else if(randNum==2){
            turnScore +=2;
            updateText();
        }
        else if(randNum==3){
            turnScore+=3;
            updateText();
        }
        else if(randNum==4){
            turnScore+=4;
            updateText();
        }
        else if(randNum==5){
            turnScore+=5;
            updateText();
        }
        else{
            turnScore+=6;
            updateText();
        }
        updateImage(randNum);
    }


    public void rollDice(View view){
        int randNum = randDice();
        if (randNum==1){
            turnScore =0;
            updateText();
            userTurn=false;
            numCompRounds=0;
            updateImage(randNum);
            computerTurn();

        }
        else if(randNum==2){
            turnScore +=2;
            updateImage(randNum);

            updateText();
        }
        else if(randNum==3){
            turnScore+=3;
            updateImage(randNum);

            updateText();
        }
        else if(randNum==4){
            turnScore+=4;
            updateText();
        }
        else if(randNum==5){
            turnScore+=5;
            updateText();
        }
        else{
            turnScore+=6;
            updateText();
        }
        updateImage(randNum);
        checkScore();
    }

    //function updates image
    public void updateImage(int dots){
        ImageView imageView = (ImageView) findViewById(R.id.dice);
        int ID;
        if(dots==1){
            ID = R.drawable.dice1;
            imageView.setContentDescription("Dice image 1");
        }
        else if(dots==2){
            ID = R.drawable.dice2;
        }
        else if(dots==3){
            ID = R.drawable.dice3;
        }
        else if(dots==4){
            ID = R.drawable.dice4;
        }
        else if(dots==5){
            ID = R.drawable.dice5;
        }
        else{
            ID = R.drawable.dice6;
        }
        ((ImageView) findViewById(R.id.dice)).setImageDrawable(
                getResources().getDrawable(ID));
    }

    //function updates text
    public void updateText(){
        int shownScoreUser;
        int shownScoreComp;

        if (userTurn){
            shownScoreUser = turnScore+totalUserScore;
            shownScoreComp = totalCompScore;
        }
        else{
            shownScoreComp=turnScore+totalCompScore;
            shownScoreUser=totalUserScore;
        }
        String newScore = "Your score: "+shownScoreUser+" Computer score: "+shownScoreComp;
        ((TextView) findViewById(R.id.text)).setText(newScore);

    }

    //function resets game
    public void reset(View view){
        totalCompScore=0;
        totalUserScore=0;
        turnScore=0;
        numCompRounds=0;
        updateText();
        updateImage(6);
        userTurn=true;
    }

    public void resetScore(){
        totalCompScore=0;
        totalUserScore=0;
        turnScore=0;
        numCompRounds=0;
        updateText();
        updateImage(6);
        userTurn=true;
    }

    public boolean checkScore(){
        if (userTurn&totalUserScore+turnScore>=15){
            playerWins();
            return true;
        }
        else if(userTurn==false&totalCompScore+turnScore>=15){
            Button rollButton = (Button) findViewById(R.id.button1);
            rollButton.setClickable(true);
            Button holdButton = (Button) findViewById(R.id.button2);
            holdButton.setClickable(true);
            computerWins();

            return true;
        }
        else{
            return false;
        }
    }



}