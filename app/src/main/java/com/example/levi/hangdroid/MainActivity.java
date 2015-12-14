package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Text;

public class MainActivity extends Activity
    {
    MediaPlayer mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    @Override
    public void onPause()
        {
        super.onPause();

        //releases the media player if activity ends
        mainMenu.release();
        }

    @Override
    public void onResume()
        {
        super.onResume();

        //creates the media player for the meain menu
        mainMenu = MediaPlayer.create(this, R.raw.intro);

        //starts the music player
        mainMenu.start();

        //sets the music to loop indefinitely
        mainMenu.setLooping(true);
        }

    //onClick for starting the single player activity
    public void SinglePlayerClick(View view)
        {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        }

    //onClick for starting the Multi Player activity
    public void MultiPlayerClick(View view)
        {
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);
        }

    //onClick for starting the scores activity
    public void ScoresClick(View view)
        {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
        }

    //onClick for starting the text game
    public void TextPlayerClick(View view)
        {
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
        }

    //onClick for starting the contacts activity
    public void ContactsClick(View view)
        {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
        }
    }
