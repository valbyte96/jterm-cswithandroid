package com.scarne.valbyte96.scarne;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scarne);
    }

    public void rollDice(View view){
        Random rand = new Random();
        int randNum = rand.nextInt((6 - 1) + 1) + 1;
        ImageView imageView = (ImageView) findViewById(R.id.dice);
        TextView scoreView = (TextView) findViewById(R.id.text);

        if (randNum==1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            turnUserScore =0;
            String newScore = "Your score:"+turnUserScore+totalUserScore+" Computer score:"+totalCompScore;
            scoreView.setText(newScore);
        }
        else if(randNum==2){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
            turnUserScore+=2;
            String newScore = "Your score:"+turnUserScore+totalUserScore+" Computer score:"+totalCompScore;
            scoreView.setText(newScore);
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
}
