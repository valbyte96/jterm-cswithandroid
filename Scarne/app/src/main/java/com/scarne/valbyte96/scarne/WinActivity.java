package com.scarne.valbyte96.scarne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        String score = intent.getStringExtra(ScarneActivity.USER_SCORE);
        ((TextView)findViewById(R.id.textView2))
                .setText(String.format("You scored %s", score));

        ((Button)findViewById(R.id.playagain2))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
    }
}
