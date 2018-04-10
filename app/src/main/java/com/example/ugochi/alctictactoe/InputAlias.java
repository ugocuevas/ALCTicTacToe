package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputAlias extends AppCompatActivity {

    public String aliasForPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_alias);
    }

    public void startGame(View view) {
        EditText nameEntered = findViewById(R.id.enter_alias);
        aliasForPlayer = nameEntered.getText().toString();
        if (aliasForPlayer.equals("")) {
            Toast.makeText(this, "Please enter an alias", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent startGameIntent = new Intent(InputAlias.this, ChooseBoard.class);
            startGameIntent.putExtra("Name of Player", aliasForPlayer);
            startGameIntent.putExtra("isHuman", false);
            startActivity(startGameIntent);
        }
    }


}
