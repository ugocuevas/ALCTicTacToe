package com.example.ugochi.alctictactoe;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoardThree extends AppCompatActivity implements View.OnClickListener{

    private boolean playerOneTurn;

    private int playerOnePoints;
    private int playerTwoPoints;
    private int movesCount;

    private TextView playerOneAlias;
    private TextView playerTwoAlias;
    private TextView whoseTurn;
    private TextView playerOne;
    private TextView playerTwo;

    private String playerOneAliasString;
    private String playerTwoAliasString;

    private String gameMode;

    Button resetGame;

    LinearLayout mainBoard;

    private Button [][] cells = new Button[3][3];
    private boolean isHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        playerOneAlias = findViewById(R.id.player_one);
        playerTwoAlias = findViewById(R.id.player_two);

        playerOneTurn = true;

        playerOne = findViewById(R.id.player_one_score);
        playerTwo = findViewById(R.id.player_two_score);
        whoseTurn = findViewById(R.id.whose_turn);

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
            gameMode = "COMPUTER";
        }

        mainBoard = findViewById(R.id.three_by_three_board);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String positionID = "position_" + i + j;
                int resID = getResources().getIdentifier(positionID, "id", getPackageName());
                cells[i][j] = findViewById(resID);
                cells[i][j].setOnClickListener(this);
            }
        }

        resetGame = findViewById(R.id.reset_game);
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    public void quit_to_main_menu(View view) {
        Intent mainMenuIntent = new Intent(GameBoardThree.this, MainActivity.class);
        startActivity(mainMenuIntent);
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
            }
        }
        movesCount = 0;
        playerOneTurn = true;
    }

    private void resetGame() {
        playerOnePoints = 0;
        playerTwoPoints = 0;
        updatePoints();
        resetBoard();
    }

    private void updatePoints() {
        playerOne.setText(String.format(" %d", playerOnePoints));
        playerTwo.setText(String.format("%d", playerTwoPoints));
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (gameMode.equals("HUMAN")){

            if (playerOneTurn) {
                ((Button) view).setText("X");
                whoseTurn.setText("Turn: " + playerTwoAliasString + "'s turn");
            } else {
                ((Button) view).setText("O");
                whoseTurn.setText("Turn: " + playerOneAliasString + "'s turn");

            }

            movesCount++;

            if (hasWon()) {
                if (playerOneTurn) {
                    playerOneWins();
                } else {
                    playerTwoWins();
                }
            } else if (movesCount == 9) {
                drawnMatch();
            } else {
                playerOneTurn = !playerOneTurn;
            }
        }
        else if (gameMode.equals("COMPUTER")) {
            if (playerOneTurn) {
                ((Button) view).setText("X");

                movesCount++;

                if (hasWon()) {
                    if (playerOneTurn) {
                        playerOneWins();
                    } else {
                        playerTwoWins();
                    }
                } else if (movesCount == 9) {
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
                int random = (int) (Math.random() * 9);
                int row = random / 3;
                int col = random % 3;

                for (int i = 0; i < row + 1; i++) {
                    for (int j = 0; j < col + 1; j++) {
                        if (cells[i][j].getText().toString().equals("")) {
                            cells[i][j].setText("O");
                            if (cells[0][0].equals(cells[1][1])
                                    && cells[0][0].getText().toString().equals("X")
                                    && !cells[0][0].getText().toString().equals("")) {
                                cells[2][2].setText("O");
                            }
                            if (cells[0][0].equals(cells[1][0])
                                    && cells[0][0].getText().toString().equals("X")
                                    && !cells[0][0].getText().toString().equals("")) {
                                cells[2][0].setText("O");
                            }
                            if (cells[0][0].equals(cells[1][1])
                                    && cells[0][0].getText().toString().equals("X")
                                    && !cells[0][0].getText().toString().equals("")){
                                cells[2][2].setText("O");
                            }
                            if (cells[i][0].equals(cells[i][1])
                                    && cells[0][0].getText().toString().equals("X")
                                    && !cells[0][0].getText().toString().equals("")){
                                cells[i][2].setText("O");
                            }

                            if (cells[0][2].equals(cells[1][1])
                                    && cells[0][2].getText().toString().equals("X")
                                    && !cells[0][2].getText().toString().equals("")){
                                cells[2][0].setText("O");
                            }
                            if (cells[0][j].equals(cells[1][j])
                                    && cells[0][j].getText().toString().equals("X")
                                    && !cells[0][j].getText().toString().equals("")){
                                cells[2][j].setText("O");
                            }

                            if (cells[2][2].equals(cells[1][1])
                                    && cells[2][2].getText().toString().equals("X")
                                    && !cells[2][2].getText().toString().equals("")){
                                cells[0][0].setText("O");
                            }

                            if (cells[2][0].equals(cells[1][1])
                                    && cells[2][0].getText().toString().equals("X")
                                    && !cells[2][0].getText().toString().equals("")){
                                cells[0][2].setText("O");
                            }

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
            } else if (movesCount == 9) {
                drawnMatch();
            } else {
                playerOneTurn = !playerOneTurn;
            }
        }

    }


    private void playerOneWins() {
        playerOnePoints++;
        Toast.makeText(this, playerOneAliasString + " wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void playerTwoWins() {
        playerTwoPoints++;
        Toast.makeText(this, playerTwoAliasString + " wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void drawnMatch() {
        Toast.makeText(this, "Oops, This match is a draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private boolean hasWon() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = cells[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", movesCount);
        outState.putInt("player1Points", playerOnePoints);
        outState.putInt("player2Points", playerTwoPoints);
        outState.putBoolean("player1Turn", playerOneTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        movesCount = savedInstanceState.getInt("roundCount");
        playerOnePoints = savedInstanceState.getInt("player1Points");
        playerTwoPoints = savedInstanceState.getInt("player2Points");
        playerOneTurn = savedInstanceState.getBoolean("player1Turn");
    }

    public void resetBoard(View view) {
        resetBoard();
    }
}
