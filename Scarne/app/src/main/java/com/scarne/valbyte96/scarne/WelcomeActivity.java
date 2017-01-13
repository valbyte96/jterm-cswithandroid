package com.scarne.valbyte96.scarne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ((Button) findViewById(R.id.startPlayButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                play();
            }
        });
    }

    private void play(){
        startActivity(new Intent(this, ScarneActivity.class));
    }
}
