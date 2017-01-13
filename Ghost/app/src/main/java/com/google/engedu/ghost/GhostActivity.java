/* Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String TAG = "GhostActivity";
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private static final String KEY_USER_TURN = "keyUserTurn";
    private static final String KEY_CURRENT_WORD = "keyCurrentWord";
    private static final String KEY_SAVED_STATUS = "keySavedStatus";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private boolean winner = false;
    private Random random = new Random();
    private String currentWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new FastDictionary(inputStream);
            // Initialize your dictionary from the InputStream.
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        if (savedInstanceState == null) {
            onStart(null);
        } else {
            userTurn = savedInstanceState.getBoolean(KEY_USER_TURN);
            currentWord = savedInstanceState.getString(KEY_CURRENT_WORD);
            String status = savedInstanceState.getString(KEY_SAVED_STATUS);
            ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
            ((TextView) findViewById(R.id.gameStatus)).setText(status);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (winner==false) {
            int unicode = event.getUnicodeChar();
            if (unicode >= 'a' && unicode <= 'z') {
                String letter = Character.toString((char) unicode);
                currentWord += letter;
                ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
                if (dictionary.isWord(currentWord)) {
                    ((TextView) findViewById(R.id.gameStatus)).setText("Computer Wins");
                    winner = true;
                } else {
                    computerTurn();
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again
        if (dictionary.isWord(currentWord)){
            label.setText("Computer Wins");
            winner=true;
        }
        else if (dictionary.getAnyWordStartingWith(currentWord)==null){
            label.setText("Word Challenged; Computer Wins");
            winner=true;
        }
        else {
            String word = dictionary.getAnyWordStartingWith(currentWord);
            int len = currentWord.length();
            String letter = word.substring(len,len+1);
            currentWord+=letter;
            ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
            if (dictionary.isWord(currentWord)){
                label.setText( getString(R.string.USER_WIN));
                winner=true;

            }
        }

        if (winner==false){
            userTurn = true;
            label.setText(USER_TURN);
        }

    }

    //handler for the challenge button
    public void challenge(View view){
        if (dictionary.isWord(currentWord)){
            ((TextView) findViewById(R.id.gameStatus)).setText( getString(R.string.USER_WIN));
        }
        else{
            ((TextView) findViewById(R.id.gameStatus)).setText("Computer wins");
            ((TextView) findViewById(R.id.ghostText)).setText(dictionary.getAnyWordStartingWith(currentWord));
        }
        winner=true;
    }
    //handler for reset
    public void reset(View view){
        winner=false;
        currentWord="";
        ((TextView) findViewById(R.id.gameStatus)).setText("Your turn");
        ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
        userTurn=false;

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_USER_TURN, userTurn);
        outState.putString(KEY_CURRENT_WORD, currentWord);
        outState.putString(KEY_SAVED_STATUS,
                ((TextView) findViewById(R.id.gameStatus)).getText().toString());
        super.onSaveInstanceState(outState);
    }
}
