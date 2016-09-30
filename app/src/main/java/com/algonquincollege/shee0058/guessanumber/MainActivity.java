package com.algonquincollege.shee0058.guessanumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import android.view.Menu;
import android.view.MenuItem;
import android.app.DialogFragment;

/**
 * {Guess a number game}
 *
 * @author owensheehan  {shee0058@algonquinlive.com}
 */

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG;
    private static final String ABOUT_DIALOG_TAG;

    static {
        LOG_TAG = "GUESS A NUMBER";
        ABOUT_DIALOG_TAG = "About Dialog";
    }

    protected int currentNumber = 0;
    protected int userGuess = 0;
    Random rand = new Random();
    int theNumber = rand.nextInt(1000) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "theNumber= " + theNumber + "");

        Button resetButton = (Button) findViewById(R.id.reset_button);
        Button guessButton = (Button) findViewById(R.id.guess_button);


        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText guessText = (EditText) findViewById(R.id.guess_text);
                String guess = guessText.getText().toString();
                int guessNumber = Integer.parseInt(guess);

                if (guess.isEmpty()) {
                    guessText.setError("You must enter a number");
                    guessText.requestFocus();
                    return;
                }

                if (guessNumber > 1000) {
                    guessText.setError("You must enter a number below 1000");
                    guessText.requestFocus();
                    return;
                }

                if (guessNumber < 1) {
                    guessText.setError("You must enter a number higher than 0");
                    guessText.requestFocus();
                    return;
                }

                userGuess++;

                if (userGuess == 10) {
                    currentNumber = 0;
                    theNumber = rand.nextInt(1000) + 1;
                    Toast.makeText(MainActivity.this, "You reached the maximum attempts. Your game has been Reset!", Toast.LENGTH_LONG).show();

                }

                if (guessNumber < theNumber) {
                    Toast.makeText(MainActivity.this, "Too Low! Try Again", Toast.LENGTH_LONG).show();
                }

                if (guessNumber > theNumber) {
                    Toast.makeText(MainActivity.this, "Too High! Try Again", Toast.LENGTH_LONG).show();
                }

                if (guessNumber == theNumber) {
                    if (userGuess <= 5) {
                        Toast.makeText(MainActivity.this, "Superior Win! You guessed it in " + userGuess + " attempts", Toast.LENGTH_LONG).show();
                    }
                    if (userGuess > 5) {
                        Toast.makeText(MainActivity.this, "Excellent Win! You guessed it in " + userGuess + " attempts", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, theNumber + "", Toast.LENGTH_LONG).show();


                return true;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentNumber = 0;
                theNumber = rand.nextInt(1000) + 1;

                Log.i(LOG_TAG, "currentNumber= " + currentNumber + "");
                Log.i(LOG_TAG, "theNumber= " + theNumber + "");

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: add this method to handle when the user selects a menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
