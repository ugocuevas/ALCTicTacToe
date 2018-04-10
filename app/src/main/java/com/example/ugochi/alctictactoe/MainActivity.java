package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gameAgainstComputer(View view) {
        Intent playComputerIntent = new Intent(MainActivity.this, InputAlias.class);
        startActivity(playComputerIntent);
    }

    public void gameAgainstHuman(View view) {
        Intent playHumanIntent = new Intent(MainActivity.this, InputAliases.class);
        startActivity(playHumanIntent);
    }

    public void viewInstructions(View view) {
        Intent instructionsIntent = new Intent(MainActivity.this, ViewInstructions.class);
        startActivity(instructionsIntent);
    }
}
