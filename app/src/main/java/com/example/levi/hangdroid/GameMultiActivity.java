package com.example.levi.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends Activity
    {
    //initializing objects used throughout
    String theWord = "";
    int goodLetterCount = 0;
    int badLetterCount = 0;
    int points = 0;
    boolean win;
    MediaPlayer newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    //displays the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_player_game, menu);
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

    //I set this up to release the media player if this activity is ended for whatever reason
    @Override
    public void onPause()
        {
        super.onPause();
        newGame.release();
        }

    //I have read that using the onResume is better for the typical onCreate items for functionality,
    //so I tried it , and it seems to work well.
    @Override
    public void onResume()
        {
        super.onResume();

        //pull layout from xml file
        setContentView(R.layout.activity_multi_game);

        //sets up the media player to play the music clip
        newGame = MediaPlayer.create(this, R.raw.newgame);

        //plays music clip
        newGame.start();

        //releases the music clip once it finishes playing so it doesn't hog memory
        newGame.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
        public void onCompletion(MediaPlayer mp)
            {
            newGame.release(); // finish current activity
            }
        });

        //get the word from the intent
        String wordToGuess;
        wordToGuess = getIntent().getStringExtra("GUESS_WORD").toUpperCase();

        //log for error testing
        Log.d("MYLOG", "Word Sent: " + wordToGuess);

        //calling a method to create spaces using the wordToGuess as the variable
        createTextViews(wordToGuess);

        //applying the player entered word into the variable "theWord"
        theWord = wordToGuess;
        }

    //instantiates the text views for the word programmatically, makes it so I can add
    //different length words in the future
    private void createTextViews(String word)
        {
        //retrieve layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //create needed number of spaces for the entered word
        for (int i = 0; i < word.length(); i++)
            {
            //calls the layout of a single letter space as
            //many times as necessary until i=word.length
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.single_letter, null);
            layout.addView(textView);
            }
        }

    //creates sll of the buttons for the alphabet and assigns them the letter value for the guess
    public void letterPressed(View view)
        {
        String letter = "";

        switch (view.getId())
            {
            case R.id.buttonA:
                letter = "A";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonB:
                letter = "B";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonC:
                letter = "C";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonD:
                letter = "D";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonE:
                letter = "E";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonF:
                letter = "F";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonG:
                letter = "G";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonH:
                letter = "H";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonI:
                letter = "I";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonJ:
                letter = "J";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonK:
                letter = "K";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonL:
                letter = "L";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonM:
                letter = "M";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonN:
                letter = "N";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonO:
                letter = "O";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonP:
                letter = "P";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonQ:
                letter = "Q";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonR:
                letter = "R";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonS:
                letter = "S";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonT:
                letter = "T";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonU:
                letter = "U";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonV:
                letter = "V";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonW:
                letter = "W";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonX:
                letter = "X";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonY:
                letter = "Y";
                view.setVisibility(View.GONE);
                break;
            case R.id.buttonZ:
                letter = "Z";
                view.setVisibility(View.GONE);
                break;
            }

        Log.d("MYLOG", "The letter is: " + letter);

        checkLetter(letter);
        }

    public void checkLetter(String letter)
        {
        char aLetter = letter.charAt(0);

        boolean letterGuessed = false;

        //for loop to check for correct or wrong letters
        for (int i = 0; i < theWord.length(); i++)
            {
            //runs the guessed letter against every letter in the word, checking if it matches
            if (theWord.charAt(i) == aLetter)
                {
                letterGuessed = true;

                //logs the correctly guessed letter
                Log.d("MYLOG", "Letter Found: " + aLetter);

                //keeps track of how many letters are guessed correctly
                goodLetterCount++;

                //displays the correctly guessed letter in the location that matches the word
                showLetter(i, aLetter);
                }
            }

        if (!letterGuessed)
            {
            //logs the incorrect guess
            Log.d("MYLOG", "Letter NOT found " + aLetter);

            //increases the count of bad guesses
            badLetterCount++;

            //displays the incorrect guess on the screen
            wrongLetter(Character.toString(aLetter));
            }

        Log.d("MYLOG", "Check for win: " + goodLetterCount + " " + theWord.length());

        //checks to see if you have won
        if (goodLetterCount == theWord.length())
            {
            //displays victory text
            Toast.makeText(this, "If one wants to truly pwn, one must pwn... in ALL games!", Toast.LENGTH_SHORT).show();

            //adds to the total points for wins
            points++;

            //determines what part of the gameOver method to run
            win = true;

            //runs game over method
            gameOver();
            }
        }

    public void wrongLetter(String badLetter)
        {
        //references the location of the wrong letter field
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        //references the string of previously incorrect guesses
        String previousLetters = textView.getText().toString();

        //logs the incorrect guess
        Log.d("MYLOG", "Bad Letter: " + badLetter + " " + badLetterCount);

        //begins adding the incorrect letters to the string
        if (badLetterCount > 1)
            {
            textView.setText(previousLetters + " " + badLetter);
            }
        else
            {
            textView.setText(badLetter);
            }
        //selects what image to display based on how many wrong answers there are
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (badLetterCount == 1)
            imageView.setImageResource(R.drawable.hangdroid_1);
        if (badLetterCount == 2)
            imageView.setImageResource(R.drawable.hangdroid_2);
        if (badLetterCount == 3)
            imageView.setImageResource(R.drawable.hangdroid_3);
        if (badLetterCount == 4)
            imageView.setImageResource(R.drawable.hangdroid_4);
        if (badLetterCount == 5)
            imageView.setImageResource(R.drawable.hangdroid_5);
        if (badLetterCount > 5)
            {
            //you lose toast, references an internet show that I love named PurePwnage
            Toast.makeText(this, "LOL - gg Kyle!", Toast.LENGTH_LONG).show();

            //determines what part of the gameOver method to run
            win = false;

            //runs game over method
            gameOver();
            }
        }

    public void showLetter(int position, char letterGuessed)
        {
        //reference the location of "layoutLetters" in our linear layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //references the location of the text view within the layout
        TextView textView = (TextView) layout.getChildAt(position);

        //fills in the space containing the correct letter guess with the letter
        textView.setText(Character.toString(letterGuessed));
        }

    public void gameOver()
        {
        if (!win)
            {
            //creates the intent
            Intent intent = new Intent(this, GameOverLoseActivity.class);

            //sends the points data with the intent
            intent.putExtra("PointsID", points);

            //starts the activity defined by the intent above
            startActivity(intent);

            //calls method to clear the game for a new game
            clearScreen();
            }
        if (win)
            {
            //creates the intent
            Intent intent = new Intent(this, GameOverWinActivity.class);

            //sends the points data with the intent
            intent.putExtra("PointsID", points);

            //starts the activity defined by the intent above
            startActivity(intent);

            //calls method to clear the game for a new game
            clearScreen();
            }
        }

    public void clearScreen()
        {
        //references the values stored in "textViewWrong"
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        //resets the values in "textViewWrong"
        textView.setText("");

        //resets counters for correct and incorrect guesses
        badLetterCount = 0;
        goodLetterCount = 0;

        //references the layout specified
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //for loop creating the blank spaces for the missing letters
        for (int i = 0; i < layout.getChildCount(); i++)
            {
            TextView childTextView = (TextView) layout.getChildAt(i);
            childTextView.setText("_");
            }

        //resets the image to the original, with no pieces hanging
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
        }

    }
