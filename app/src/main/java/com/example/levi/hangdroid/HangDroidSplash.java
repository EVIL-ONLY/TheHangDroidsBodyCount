package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HangDroidSplash extends Activity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    @Override
    protected void onResume()
        {
        super.onResume();

        //sets the layout
        setContentView(R.layout.activity_hang_droid_splash);

        //create runnable class to begin the next activity
        Runnable wait3seconds = new Runnable()
        {
        @Override
        public void run()
            {
            nextActivity();
            }
        };

        //creates handler to execute the activity
        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);
        }

    public void nextActivity()
        {
        //calls the main menu activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        }
    }
