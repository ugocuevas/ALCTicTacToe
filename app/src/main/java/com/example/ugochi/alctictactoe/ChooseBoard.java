package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseBoard extends AppCompatActivity {

    private String alias, aliasOne, aliasTwo;
    private boolean isAliasHuman;
    private Intent intentFromInputAlias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_board);

        intentFromInputAlias = getIntent();
//        Intent intentFromInputAliases = getIntent();
//        if (intentFromInputAlias.hasExtra("Name of Player")){
//            alias = intentFromInputAlias.getStringExtra("Name of Player");
//        }
//
//        if (intentFromInputAliases.hasExtra("isHuman")){
//            isAliasHuman = intentFromInputAliases.getBooleanExtra("isHuman", false);
//            if (intentFromInputAliases.hasExtra("Player One Alias")){
//                aliasOne = intentFromInputAliases.getStringExtra("Player One Alias");
//            }
//            if (intentFromInputAliases.hasExtra("Player Two Alias")){
//                aliasTwo = intentFromInputAliases.getStringExtra("Player Two Alias");
//            }
//        }
    }


    public void threeByThree(View view) {
        Intent threeByThreeBoardIntent = new Intent(ChooseBoard.this, GameBoardThree.class);
        threeByThreeBoardIntent.putExtras(this.intentFromInputAlias);
        startActivity(threeByThreeBoardIntent);
    }

    public void fiveByFive(View view) {
        Intent fiveByFiveBoardIntent = new Intent(ChooseBoard.this, GameBoardFive.class);
        fiveByFiveBoardIntent.putExtras(this.intentFromInputAlias);
        startActivity(fiveByFiveBoardIntent);
    }
}
