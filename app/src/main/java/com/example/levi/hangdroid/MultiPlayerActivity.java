package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MultiPlayerActivity extends Activity
    {

    //instantiates the text editor
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_multi_player);

        editText = (EditText) findViewById(R.id.editTextWord);

        //text watcher for entered letters
        editText.addTextChangedListener(new TextWatcher()
        {
        @Override
        //log option for checking for errors
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            Log.d("MYLOG", "beforeTextChanged" + " Start: " + start + " Count: " + count + " After: " + after);
            }

        //log option for checking for errors
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            Log.d("MYLOG", "onTextChange" + " Start: " + start + " Before: " + before + " Count: " + count);
            }

        //log option for checking for errors
        @Override
        public void afterTextChanged(Editable s)
            {
            Log.d("MYLOG", "afterTextChanged " + s);
            }
        });
        }

    //opens the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_player, menu);
        return true;
        }

    //action bar item execution
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
        {
        switch (item.getItemId())
            {
            //takes player to the main menu
            case R.id.action_main_menu:

                Intent mainMenu = new Intent(this, MainActivity.class);
                startActivity(mainMenu);

                return true;

            //takes the player to the scores screen
            case R.id.action_scores:

                Intent scores = new Intent(this, ScoresActivity.class);
                startActivity(scores);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

            }
        }

    public void playMultiPlayerGame(View view)
        {
        //creates the editText object
        EditText editText = (EditText) findViewById(R.id.editTextWord);

        //get the player entered word and cast it to a string
        String wordToGuess = editText.getText().toString();

        //debug line
        Log.d("MYLOG", "Player Entered Word: " + wordToGuess);

        //create intent to run MultiPlayerGame
        Intent intent = new Intent(this, GameMultiActivity.class);

        //send the word with the intent
        intent.putExtra("GUESS_WORD", wordToGuess);

        //start the game
        startActivity(intent);
        }

    }
