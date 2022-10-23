package edu.ncc.tictactoe;

/**
 * <p>Title: tic tac toe - PlayersNamesActivity Class</p>
 *
 * <p>Description: This activity is the launcher activity.
 * It saves the name of the players and launches a new Activity which is the MainActivity. </p>
 *
 * @author Alireza Farhangi
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayersNamesActivity extends AppCompatActivity {


    /**
     * onCreate --
     * It gets called when the app launches.
     * @param savedInstanceState - Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_names);
    }


    /**
     * storeName --
     * It gets called when the players enter their names.
     * It saves the names via two Intent objects which both will be retrieved in the MainActivity.
     * In addition, it launches a new activity which is the MainActivity.
     * @param v - View
     */
    public void storeName(View v)
    {

        // getting the name of the player1
        EditText eText1 = ((EditText)findViewById(R.id.edit_name1));

        // casting EditText to String
        String player1Name = eText1.getText().toString();

        // getting the name of the player2
        EditText eText2 = ((EditText)findViewById(R.id.edit_name2));

        // casting EditText to String
        String player2Name = eText2.getText().toString();

        // checking if the players have been entered their names
        if(player1Name.equals("") || player2Name.equals("") )
        {
            Toast.makeText(getApplicationContext(), "Please enter the players names", Toast.LENGTH_LONG).show();

            // The app should stay in the launcher activity because the names have not been appropriately added
            Intent intent2 = new Intent(this, PlayersNamesActivity.class);
            startActivity(intent2);
        }

        else
        {
            //  specifying which activity(MainActivity) to be launched after the users have been appropriately added their names
            Intent intent = new Intent(this, MainActivity.class);

            // saving the name of the player 1 which will be used in the MainActivity
            intent.putExtra("player1 name", player1Name);

            // saving the name of the player 2 which will be used in the MainActivity
            intent.putExtra("player2 name", player2Name);

            // launch a new activity(MainActivity)
            startActivity(intent);
        }

    }

}
