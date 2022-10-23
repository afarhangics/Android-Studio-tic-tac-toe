package edu.ncc.tictactoe;
/**
 *
 * Copyright (C) 2019 The CSC 240 Instructors & Students.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/**
 * <p>Title: tic tac toe - MainActiviy Class</p>
 *
 * <p>Description: This Activity displays the winner of the tic tac toe game. It also displays if there's a draw.
 * It gives the option of changing the color of X's and O's to the players.
 * In addition, displays whose turn it is and players can change the orientation of the device.</p>
 *
 * @author Alireza Farhangi
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // stores the buttons in the btns array
    private Button[] btns;

    // counter for the number of times that the buttons have been clicked
    private int count;

    // stores the color of X's
    private int xColor;

    // stores the color of O's
    private int oColor;

    // stores the name of the player1
    private String player1Name;

    // stores the name of the player2
    private String player2Name;


    /**
     * onCreate --
     * It gets called when the Activity launches. Also, retrieves the intent object and players names.
     * In addition, sets the color of X and O and set the listener for buttons.
     * @param savedInstanceState - Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // retrieving the intent object in order to get the names of the players
        Intent intent = getIntent();

        // getting the names of the player 1 and 2
        player1Name = intent.getStringExtra("player1 name");
        player2Name = intent.getStringExtra("player2 name");

        // setting the color of X's and O's
        xColor = Color.BLACK;
        oColor = Color.BLACK;

        // create the array of buttons
        btns = new Button[9];
        // the id of the first button
        int btnId = R.id.btn1;

        // loop through the buttons, store in the array & set up listener
        for (int i = 0; i < btns.length; i++)
        {
            btns[i] = (Button) findViewById(btnId);
            // listening for the click event. if we don't have this line, onClick method won't get called
            btns[i].setOnClickListener(this);
            btnId++;
        }

        count = 0;

    }


    /**
     * onCreateOptionsMenu --
     * It adds items to the action bar such as spinner.
     * @param menu - Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem spinnerItem = menu.findItem(R.id.color_spinner);

        Spinner spinner = (Spinner) spinnerItem.getActionView();

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return true;
    }



    /**
     * onOptionsItemSelected --
     * This method handles action bar item clicks.
     * @param item - MenuItem
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        // if reset button has been clicked
        else if (id == R.id.action_reset)
        {
            reset();
        }

        return super.onOptionsItemSelected(item);

    }


    /**
     * reset --
     * Resets all of the buttons to their default text which is blank.
     * In addition, sets the buttons' text colors to black and make the buttons clickable.
     */
    private void reset() {

        // setts the color of X's and O's to black
        xColor = Color.BLACK;
        oColor = Color.BLACK;

        // displays whose turn it is
        Toast.makeText(getApplicationContext(), "It's " + player1Name + "'s turn", Toast.LENGTH_LONG).show();

        /* count should be assigned to 0 because the reset button has been clicked.
        Also, all of the buttons should return to being blank.
        */
        for (int i = 0; i < btns.length; i++)
        {

            btns[i].setText(R.string.blank);
            btns[i].setTextColor(Color.BLACK);

            // user can click a button
            btns[i].setClickable(true);
        }
        count = 0;
    }


    /**
     * onClick --
     * It handles the button clicks.
     * Depending on the clicks, sets the buttons to X or O. In addition, displays who the winner is and whose turn it is.
     * @param v  - View
     */
    @Override
    public void onClick(View v) {

        // getting the id of the button
        int id = v.getId();

        /* subtracting the id of the first button from the id we got in order to get
        the index of that button in the array of buttons(btns)
        */
        int index = id - R.id.btn1;

        // if player 1 clicks a button
        if (count % 2 == 0)
        {
            btns[index].setText(R.string.x);
            btns[index].setTextColor(xColor);

            // displays whose turn it is
            Toast.makeText(getApplicationContext(), "It's " + player2Name + "'s turn", Toast.LENGTH_LONG).show();

        }

        // if player 2 clicks a button
        else {
            btns[index].setText(R.string.o);
            btns[index].setTextColor(oColor);

            // displays whose turn it is
            Toast.makeText(getApplicationContext(), "It's " + player1Name + "'s turn", Toast.LENGTH_LONG).show();
        }

        // prevents user from clicking a button that has already been clicked
        btns[index].setClickable(false);

        // each time the user clicks a button, count should be incremented by 1
        count++;


        // checking the winner of the game or if there's a draw
        if (count > 4)
        {
            String winner = " ";
            winner = checkWinner();

            if (winner.equals("X"))
            {
                Toast.makeText(getApplicationContext(), player1Name + " is the winner!", Toast.LENGTH_LONG).show();
                for (int i = 0; i < btns.length; i++)
                {
                    btns[i].setClickable(false);
                }
            }

            else if(winner.equals("O"))
            {
                Toast.makeText(getApplicationContext(), player2Name + " is the winner!", Toast.LENGTH_LONG).show();
                for (int i = 0; i < btns.length; i++)
                {
                    btns[i].setClickable(false);
                }

            }

            else if(count > 8)
            {
                Toast.makeText(getApplicationContext(), "Draw!!", Toast.LENGTH_LONG).show();

            }
        }

    }


    /**
     * onItemSelected --
     * It changes the colors of X and O when players change their colors via spinner.
     * @param parent  - AdapterView<?>
     * @param view - View
     * @param position - int
     * @param  id - long
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // changing the buttons' text colors depending on which user has clicked a button
        if (count % 2 == 0) {
            xColor = Color.BLACK;
            switch (position) {
                case 1:
                    xColor = Color.MAGENTA;
                    break;

                case 2:
                    xColor = Color.CYAN;
                    break;
            }
        }

        else {
            oColor = Color.BLACK;
            switch (position) {

                case 1:
                    oColor = Color.MAGENTA;
                    break;

                case 2:
                    oColor = Color.CYAN;
                    break;
            }

        }

        // setting the buttons' text colors
        for (int i = 0; i < btns.length; i++) {
            if (btns[i].getText().toString().equals("X"))
            {
                btns[i].setTextColor(xColor);

            }
            else {
                btns[i].setTextColor(oColor);

            }

        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * checkWinner --
     * determines the winner of the game or if there's a draw.
     * @return returns the value stored in the winner
     */
    public String checkWinner()
    {
        // getting the buttons' texts
        String btns0 = btns[0].getText().toString();
        String btns1 = btns[1].getText().toString();
        String btns2 = btns[2].getText().toString();
        String btns3 = btns[3].getText().toString();
        String btns4 = btns[4].getText().toString();
        String btns5 = btns[5].getText().toString();
        String btns6 = btns[6].getText().toString();
        String btns7 = btns[7].getText().toString();
        String btns8 = btns[8].getText().toString();

        String xWinner = "X";
        String oWinner = "O";

        String winner = " ";

        // check for the 8 conditions that a user can win the game
        for (int i = 0; i < 8; i++) {
            String result = " ";
            switch (i) {
                case 0:
                    result = btns0 + btns1 + btns2;
                    break;
                case 1:
                    result = btns3 + btns4 + btns5;
                    break;
                case 2:
                    result = btns6 + btns7 + btns8;
                    break;
                case 3:
                    result = btns0 + btns3 + btns6;
                    break;
                case 4:
                    result = btns1 + btns4 + btns7;
                    break;
                case 5:
                    result = btns2 + btns5 + btns8;
                    break;
                case 6:
                    result = btns0 + btns4 + btns8;
                    break;
                case 7:
                    result = btns2 + btns4 + btns6;
                    break;
            }

            // determines who the winner is
            if(result.equals("XXX"))
            {
                winner = xWinner;
            }
            else if(result.equals("OOO"))
            {
                winner = oWinner;
            }

        }
        return winner;

    }



    /**
     * onSaveInstanceState --
     * It gets called when the user changes the rotation of the device.
     * It saves the instance variables as well as other variables that need to be saved when the rotation occurs.
     * @param outState  - Bundle
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {

        // saving the number of clicks
        outState.putInt("click count", count);

        // saving the color of X
        outState.putInt("x color", xColor);

        // saving the color of O
        outState.putInt("o color", oColor);

        // saving the name of the player 1
        outState.putString("player1 name", player1Name);

        // saving the name of the player 2
        outState.putString("player2 name", player2Name);

        String[] btnText = new String[btns.length];
        int[] btnTextColor = new int[btns.length];
        boolean[] btnClickable = new boolean[btns.length];


        for(int i=0; i < btns.length; i++)
        {
            // copying the text of each button
            btnText[i] = btns[i].getText().toString();

            // copying the color of each text
            btnTextColor[i] = btns[i].getCurrentTextColor();

            // copying if the buttons are clickable or not
            btnClickable[i] = btns[i].isClickable();

        }

        // saving the text of each button
        outState.putStringArray("button text", btnText);

        // saving the color of each button
        outState.putIntArray("button text color", btnTextColor);

        // saving if the buttons are clickable or not
        outState.putBooleanArray("button clickable", btnClickable);

        super.onSaveInstanceState(outState);
    }


    /**
     * onRestoreInstanceState --
     * It gets called when the app wants to restore information that have been saved.
     * It retrieves the instance variables as well as other variables that have been saved during the rotation.
     * @param inState  - Bundle
     */
    @Override
    public void onRestoreInstanceState(Bundle inState)
    {

        super.onRestoreInstanceState(inState);

        // retrieving the number of clicks
        count = inState.getInt("click count");

        // retrieving the X color
        xColor = inState.getInt("x color");

        // retrieving the O color
        oColor = inState.getInt("o color");

        // retrieving the name of the player 1
        player1Name = inState.getString("player1 name");

        // retrieving the name of the player 2
        player2Name = inState.getString("player2 name");

        // retrieving the text of each button
        String[] btnText = inState.getStringArray("button text");

        // retrieving the color of each text
        int[] btnTextColor = inState.getIntArray("button text color");

        // retrieving the state of each button in case of being clickable or nor 
        boolean[] btnClickable = inState.getBooleanArray("button clickable");


        for(int i = 0; i < btns.length; i++)
        {
            // setting the text of each button
            btns[i].setText(btnText[i]);

            // setting the color of each text
            btns[i].setTextColor(btnTextColor[i]);

            // setting the state of each button in case of being clickable or not
            btns[i].setClickable(btnClickable[i]);

        }

    }

}
