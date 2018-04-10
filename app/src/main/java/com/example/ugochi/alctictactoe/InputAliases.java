package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputAliases extends AppCompatActivity {

    EditText playerOneName, playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_aliases);
    }

    public void startHumanGame(View view) {
        playerOneName = findViewById(R.id.enter_player_one_alias);
        playerTwoName = findViewById(R.id.enter_player_two_alias);
        String playerOneAlias = playerOneName.getText().toString();
        String playerTwoAlias = playerTwoName.getText().toString();

        if ((playerOneAlias.equals("")) || (playerTwoAlias.equals(""))){
            Toast.makeText(this, "Guys, this is not allowed! Each player must enter an alias", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent startHumanGame = new Intent(InputAliases.this, ChooseBoard.class);
            startHumanGame.putExtra("Player One Alias", playerOneAlias);
            startHumanGame.putExtra("Player Two Alias", playerTwoAlias);
            startHumanGame.putExtra("isHuman", true);
            startActivity(startHumanGame);
        }

    }
}
