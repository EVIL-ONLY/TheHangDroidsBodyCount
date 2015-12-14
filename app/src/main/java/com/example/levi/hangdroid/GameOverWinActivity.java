package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverWinActivity extends Activity
    {
    MediaPlayer lose;

    private int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);

        //creates the media player to play sound clip
        lose = MediaPlayer.create(this, R.raw.bodycount);

        //calls the layout for this activity
        setContentView(R.layout.activity_game_over_win);

        //starts the music
        lose.start();

        //releases the music once the file is complete
        lose.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
        public void onCompletion(MediaPlayer mp)
            {
            lose.release(); // finish current activity
            }
        });

        //references the points total so far
        int points = getIntent().getIntExtra("PointsID", 0);

        TextView textView = (TextView) findViewById(R.id.textViewPoints);
        textView.setText(String.valueOf(points));

        playerPoints = points;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
        }

    //sets options for clicking in the action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
        {
        switch (item.getItemId())
            {
            case R.id.action_main_menu:

                //takes you to the main menu activity
                Intent mainMenu = new Intent(this, MainActivity.class);
                startActivity(mainMenu);

                return true;

            case R.id.action_new_single_player:

                //takes you to the single player activity
                Intent newGame = new Intent(this, GameActivity.class);
                startActivity(newGame);

                return true;

            case R.id.action_new_multi_player:

                //takes you to the multi player activity
                Intent newMultiGame = new Intent(this, MultiPlayerActivity.class);
                startActivity(newMultiGame);

                return true;

            case R.id.action_scores:

                //takes you to the scores activity
                Intent scores = new Intent(this, ScoresActivity.class);
                startActivity(scores);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

            }
        }


    //to release the music player if the page is left before finished playing
    @Override
    public void onPause()
        {
        super.onPause();
        lose.release();
        }

    public void saveScore(View view)
        {
        //calls for information in the shared preferences using "WORD_SCORES"
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);

        //names the edit text field
        EditText editText = (EditText) findViewById(R.id.editTextName);

        //assigns the text entered into the edit text field to "name"
        String name = editText.getText().toString();

        //if there is no name entered
        if (name == "")
            {
            //ask for a real entry
            Toast.makeText(this, "Please enter at least one letter.", Toast.LENGTH_LONG).show();
            }
        //if at least one character is entered
        else
            {
            //calls method to edit shared preferences
            SharedPreferences.Editor editor = preferences.edit();

            //retrieves the string using the key "SCORES"
            String previousScores = preferences.getString("SCORES", "");

            //logs the previous scores
            Log.d("MYLOG", "Previous Scores: " + previousScores);

            //edits the shared preferences to append the new score and name entered to the previous string
            editor.putString("SCORES", name + " " + playerPoints + " Point(s)\n" + previousScores);

            //commits the changes
            editor.commit();

            //toasts that the score was saved
            Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();

            //resets the text after entered
            editText.setText("");
            }
        }
    }