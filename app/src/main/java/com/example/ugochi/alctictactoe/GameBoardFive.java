package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameBoardFive extends AppCompatActivity implements View.OnClickListener {

    private Button[][] cells = new Button[5][5];

    private boolean playerOneTurn = true;

    private int movesCount;

    private int playerOnePoints;
    private int playerTwoPoints;

    private TextView playerOneAlias;
    private TextView playerTwoAlias;
    private TextView pointsForPlayerOne;
    private TextView pointsForPlayerTwo;

    private String playerOneAliasString;
    private String playerTwoAliasString;

    private String gameMode;

    Button resetGame;

    LinearLayout mainBoard;

    private boolean isHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board_five);

        playerOneAlias = findViewById(R.id.player_one);
        playerTwoAlias = findViewById(R.id.player_two);

        playerOneTurn = true;

        pointsForPlayerOne = findViewById(R.id.player_one_score);
        pointsForPlayerTwo = findViewById(R.id.player_two_score);

        playerOnePoints = 0;
        playerTwoPoints = 0;

        Intent intentFromChooseBoard = getIntent();
        isHuman = intentFromChooseBoard.getBooleanExtra("isHuman", false);

        if (isHuman) {
            playerOneAliasString = intentFromChooseBoard.getStringExtra("Player One Alias");
            playerOneAlias.setText(String.format("%s%s", playerOneAliasString, getString(R.string.colon)));
            playerTwoAliasString = intentFromChooseBoard.getStringExtra("Player Two Alias");
            playerTwoAlias.setText(String.format("%s%s", playerTwoAliasString, getString(R.string.colon)));
            gameMode = "HUMAN";
        }
        else {
            playerOneAliasString = intentFromChooseBoard.getStringExtra("Name of Player");
            playerOneAlias.setText(String.format("%s%s", playerOneAliasString, getString(R.string.colon)));
            playerTwoAliasString = "AI";
            playerTwoAlias.setText(String.format("%s%s", playerTwoAliasString, getString(R.string.colon)));
            gameMode ="COMPUTER";
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String positionID = "position_" + i + j;
                int resID = getResources().getIdentifier(positionID, "id", getPackageName());
                cells[i][j] = findViewById(resID);
                cells[i][j].setOnClickListener(this);
            }
        }

        resetGame = findViewById(R.id.reset_game);
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (gameMode.equals("HUMAN")) {
            if (playerOneTurn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }

            movesCount++;

            if (hasWon()) {
                if (playerOneTurn) {
                    playerOneWins();
                } else {
                    playerTwoWins();
                }
            } else if (movesCount == 25) {
                drawnMatch();
            } else {
                playerOneTurn = !playerOneTurn;
            }
        }
        else if (gameMode.equals("COMPUTER")) {
            if (playerOneTurn) {
                ((Button) v).setText("X");

                movesCount++;

                if (hasWon()) {
                    if (playerOneTurn) {
                        playerOneWins();
                    } else {
                        playerTwoWins();
                    }
                } else if (movesCount == 25) {
                    drawnMatch();
                } else {
                    playerOneTurn = !playerOneTurn;
                }
                computerPlay();
            }
        }
    }

    private void computerPlay() {
        boolean hasComputerPlayed = false;

        if (!playerOneTurn) {

            while (!hasComputerPlayed) {
                int random = (int) (Math.random() * 25);
                int row = random / 5;
                int col = random % 5;


                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (cells[i][j].getText().toString().equals("")) {
                            cells[i][j].setText("O");
                            hasComputerPlayed = true;
                            break;
                        }
                    }
                    if (hasComputerPlayed) {
                        break;
                    }
                }
            }

            movesCount++;

            if (hasWon()) {
                if (playerOneTurn) {
                    playerOneWins();
                } else {
                    playerTwoWins();
                }
            } else if (movesCount == 25) {
                drawnMatch();
            } else {
                playerOneTurn = !playerOneTurn;
            }
        }

    }

    private boolean hasWon() {
        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = cells[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[i][1].equals(field[i][2])
                    && field[i][1].equals(field[i][3])
                    && field[i][1].equals(field[i][4])
                    && !field[i][1].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[1][i].equals(field[2][i])
                    && field[1][i].equals(field[3][i])
                    && field[1][i].equals(field[4][i])
                    && !field[1][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[1][1].equals(field[2][2])
                && field[1][1].equals(field[3][3])
                && field[1][1].equals(field[4][4])
                && !field[1][1].equals("")) {
            return true;
        }

        if (field[1][0].equals(field[2][1])
                && field[1][0].equals(field[3][2])
                && field[1][0].equals(field[4][3])
                && !field[1][0].equals("")) {
            return true;
        }

        if (field[0][1].equals(field[1][2])
                && field[0][1].equals(field[2][3])
                && field[0][1].equals(field[3][4])
                && !field[0][1].equals("")) {
            return true;
        }


        if (field[1][3].equals(field[2][2])
                && field[1][3].equals(field[3][1])
                && field[1][3].equals(field[4][0])
                && !field[1][3].equals("")) {
            return true;
        }

        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[3][1])
                && field[0][4].equals(field[2][2])
                && !field[0][4].equals("")) {
            return true;
        }

        if (field[0][3].equals(field[1][2])
                && field[0][3].equals(field[2][1])
                && field[0][3].equals(field[3][0])
                && !field[0][3].equals("")) {
            return true;
        }

        if (field[1][4].equals(field[2][3])
                && field[1][4].equals(field[3][2])
                && field[1][4].equals(field[4][1])
                && !field[1][4].equals("")) {
            return true;
        }


        return false;
    }

    private void playerOneWins() {
        playerOnePoints++;
        Toast.makeText(this, playerOneAliasString + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playerTwoWins() {
        playerTwoPoints++;
        Toast.makeText(this, playerTwoAliasString + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void drawnMatch() {
        Toast.makeText(this, "Oops, This match is a draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        pointsForPlayerOne.setText(String.format(" %d", playerOnePoints));
        pointsForPlayerTwo.setText(String.format(" %d", playerTwoPoints));
    }

    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j].setText("");
            }
        }

        movesCount = 0;
        playerOneTurn = true;
    }

    private void resetGame() {
        playerOnePoints = 0;
        playerTwoPoints = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("movesCount", movesCount);
        outState.putInt("player1Points", playerOnePoints);
        outState.putInt("player2Points", playerTwoPoints);
        outState.putBoolean("player1Turn", playerOneTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        movesCount = savedInstanceState.getInt("movesCount");
        playerOnePoints = savedInstanceState.getInt("player1Points");
        playerTwoPoints = savedInstanceState.getInt("player2Points");
        playerOneTurn = savedInstanceState.getBoolean("player1Turn");
    }

}
