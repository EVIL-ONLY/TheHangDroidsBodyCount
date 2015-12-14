package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextActivity extends Activity
    {
    private SharedPreferences preferences;
    private TextView textView;
    private String textWord;
    private String friendPhone;
    private String friendName;
    private String texterPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        //assigns the shared preferences for "TEXT_MSGS" to a string named preferences
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);

        //get the phone number associated with the shared preferences
        friendPhone = getIntent().getStringExtra("Phone");

        //get the name associated with the shared preferences
        friendName = getIntent().getStringExtra("Name");

        //log of the information for error checking
        Log.d("MYLOG", "Friend: " + friendName + " : " + friendPhone);

        //run method
        getTextFromPref();
        }

    public void setTextMessage(View view)
        {
        //this is to refresh the text from the button
        getTextFromPref();
        }

    public void getTextFromPref()
        {
        //assigns the word from the shared preferences
        textWord = preferences.getString("TextedWord", "NO WORD");
        //assigns the phone number from the shared preferences
        texterPhone = preferences.getString("TexterPhone", "NO PHONE");

        textView = (TextView) findViewById(R.id.editTextWord);

        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if (textWord == "NO WORD") word = false;
        if (texterPhone == "NO PHONE") phone = false;
        if (friendPhone == null) friend = false;

        //boolean logic to sort whether the friend information is present, if not, it returns
        if (!friend && word)
            {
            textView.setText(textWord);
            textWord = "";
            texterPhone = "";
            return;
            }

        //boolean logic to assign text to phone contact
        if (word && phone)
            {
            if (friendPhone.equals(texterPhone))
                {
                textView.setText(textWord);
                textWord = "";
                texterPhone = "";
                }
            //if no new message from your friend
            else
                {
                Toast.makeText(this, "No New Text From the Selected Friend", Toast.LENGTH_LONG).show();
                }
            return;
            }

        //if there is no text received
        if (!word)
            {
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
            }
        }

    public void playMultiPlayerGame(View view)
        {
        //calls the multi player game but uses the texted word instead of input word
        String textWord = textView.getText().toString().toUpperCase();
        //checks to see if something was entered or not
        if (textWord.length() > 0)
            {
            textView.setText("");
            preferences.edit().remove("TextedWord").commit();
            Log.d("MYLOG", "Removed Texted Word: " + textView);
            Intent intent = new Intent(this, GameMultiActivity.class);
            intent.putExtra("GUESS_WORD", textWord);
            startActivity(intent);
            }
        else
            {
            Toast.makeText(this, "No Word Found, try 'Get New Text Word' button", Toast.LENGTH_LONG).show();
            }
        }
    }
