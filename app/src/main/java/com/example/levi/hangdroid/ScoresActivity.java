package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoresActivity extends Activity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_scores);

        //pulls the sharedPreferences for "WORD_SCORES"
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);

        //assigns the string from the shared preferences to a local string
        String scores = preferences.getString("SCORES", "NO SCORES");

        TextView textView = (TextView) findViewById(R.id.textViewScores);

        //sets the text view to display the scores
        textView.setText(scores);
        }

    //action bar menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        getMenuInflater().inflate(R.menu.menu_scores, menu);
        return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
        {
        switch (item.getItemId())
            {

            //action bar option to reset all of the scores that have been saved so far
            case R.id.action_reset_scores:

                //pulls the shared preferences string holding the scores
                SharedPreferences preferences = getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);

                //sets the value to a local string
                String previousScores = preferences.getString("SCORES", "");

                //created the shared preferences editor
                SharedPreferences.Editor editor = preferences.edit();

                //log to show what the scores were previously
                Log.d("MYLOG", "Previous Scores: " + previousScores);

                //assigns a blank space to the scores value in the shared preferences
                editor.putString("SCORES", "");

                //commits the changes
                editor.commit();

                //log shows the scores after deletion
                Log.d("MYLOG", "Scores After Deletion: " + previousScores);

                //refreshes the scores screen after clearing the scores
                Intent scoresReset = new Intent(this, ScoresActivity.class);
                startActivity(scoresReset);

                return true;

            //takes the player back to the main menu
            case R.id.action_main_menu:

                Intent mainMenu = new Intent(this, MainActivity.class);
                startActivity(mainMenu);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

            }
        }

    }

