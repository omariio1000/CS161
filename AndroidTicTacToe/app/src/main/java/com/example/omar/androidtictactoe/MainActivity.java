package com.example.omar.androidtictactoe;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//on click listener is like action listener in swing

    Button[][] grid = new Button[3][3];//keeping track of gui buttons
    Button clear;//clearing the board
    int[][] board = new int[3][3];//keeping track of values assigned to gui buttons
    final int BLANK = 0;//blank square
    final int X_MOVE = 1;//x
    final int O_MOVE = 2;//o
    int combo;//color changing
    TextView xLabel;//x wins label
    TextView oLabel;//o wins label
    int xWins = 0;//x win count
    int oWins = 0;//o win count


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getting buttons and textViews from activity_main.xml
        grid[0][0] = (Button)this.findViewById(R.id.button1);
        grid[0][1] = (Button)this.findViewById(R.id.button2);
        grid[0][2] = (Button)this.findViewById(R.id.button3);
        grid[1][0] = (Button)this.findViewById(R.id.button4);
        grid[1][1] = (Button)this.findViewById(R.id.button5);
        grid[1][2] = (Button)this.findViewById(R.id.button6);
        grid[2][0] = (Button)this.findViewById(R.id.button7);
        grid[2][1] = (Button)this.findViewById(R.id.button8);
        grid[2][2] = (Button)this.findViewById(R.id.button9);
        clear = (Button)this.findViewById(R.id.clearBoard);
        xLabel = (TextView)this.findViewById(R.id.xWins);
        oLabel = (TextView)this.findViewById(R.id.oWins);

        //formatting buttons and adding onClickListener
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y].setTextSize(48);
                grid[x][y].setTextColor(Color.BLACK);
                grid[x][y].setOnClickListener(this);
            }
        }
        clear.setOnClickListener(this);
        clear.setEnabled(false);//disabling clear button as default
        xLabel.setText("X wins: " + xWins);//setting text for x label
        oLabel.setText("O wins: " + oWins);
        xLabel.setGravity(Gravity.CENTER);//centering text
        oLabel.setGravity(Gravity.CENTER);//centering text
    }


    @Override
    public void onClick(View v) {
        Button b = (Button)v;//giving button a local value
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (b.equals(grid[x][y])) {//if the click is any of the buttons
                    if (!checkWin(X_MOVE) && !checkWin(O_MOVE) && !checkTie()) {//if nobody has won
                        if (board[x][y] == BLANK) {//x's move
                            b.setText("X");
                            b.setEnabled(false);
                            board[x][y] = X_MOVE;
                            //check win
                            if (!checkTie() && !checkWin(X_MOVE) && !checkWin (O_MOVE)) {//o's (ai) move
                                aiMove();
                            }
                        }
                        if (checkWin(X_MOVE)) {//if x wins
                            Toast.makeText(this, "X Wins!", Toast.LENGTH_LONG).show();
                            setColor(combo);
                            xWins++;
                            xLabel.setText("X wins: " + xWins);
                            oLabel.setText("O wins: " + oWins);
                            clear.setEnabled(true);
                        }
                        if (checkWin(O_MOVE)) {//if o (ai) wins
                            Toast.makeText(this, "O Wins!", Toast.LENGTH_LONG).show();
                            setColor(combo);
                            oWins++;
                            xLabel.setText("X wins: " + xWins);
                            oLabel.setText("O wins: " + oWins);
                            clear.setEnabled(true);
                        }
                        if (checkTie()) {//if the game is a tie
                            Toast.makeText(this, "Tie Game.", Toast.LENGTH_LONG).show();
                            clear.setEnabled(true);
                        }
                    }
                }
            }
        }
        if (b.equals(clear)) {
            clearBoard();
            resetColor();
        }
    }

    private void clearBoard() {//clearing board
        for (int a = 0; a < board.length; a++) {
            for (int b = 0; b < board[0].length; b++) {
                board[a][b] = BLANK;//clearing the value on the board
                grid[a][b].setText("");//clearing gui text
                grid[a][b].setEnabled(true);//enabling all buttons
            }
        }
        clear.setEnabled(false);//disabling clear button
    }

    public void aiMove() {//ai's move
        //try to win
        if (checkSingleBlank(O_MOVE)) {
            return;
        }
        //try to block
        if (checkSingleBlank(X_MOVE)) {
            return;
        }
        //play randomly
        ArrayList <Integer> list = new ArrayList<Integer>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == BLANK) {
                    list.add(x*10+y);
                }
            }
        }
        int choice = (int)(Math.random() * list.size());
        board[list.get(choice) / 10][list.get(choice) % 10] = O_MOVE;
        grid[list.get(choice) / 10][list.get(choice) % 10].setText("O");
        grid[list.get(choice) / 10][list.get(choice) % 10].setEnabled(false);

    }

    public boolean checkSingleBlank(int player) {
        int oCount = 0;
        int blankCount = 0;
        int blankX = 0;
        int blankY = 0;
        //check column win
        for (int x = 0; x < 3; x++) {
            oCount = 0;
            blankCount = 0;
            blankX = 0;
            blankY = 0;
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == BLANK) {
                    blankCount++;
                    blankX = x;
                    blankY = y;
                }
                if (board[x][y] == player) {
                    oCount++;
                }
            }
            if (oCount == 2 && blankCount == 1) {
                board[blankX][blankY] = O_MOVE;
                grid[blankX][blankY].setText("O");
                grid[blankX][blankY].setEnabled(false);
                return true;
            }
        }
        //check row win
        for (int y = 0; y < 3; y++) {
            oCount = 0;
            blankCount = 0;
            blankX = 0;
            blankY = 0;
            for (int x = 0; x < 3; x++) {
                if (board[x][y] == BLANK) {
                    blankCount++;
                    blankX = x;
                    blankY = y;
                }
                if (board[x][y] == player) {
                    oCount++;
                }
            }
            if (oCount == 2 && blankCount == 1) {
                board[blankX][blankY] = O_MOVE;
                grid[blankX][blankY].setText("O");
                grid[blankX][blankY].setEnabled(false);
                return true;
            }
        }
        //check diagonal win

        //check top left to bottom right
        oCount = 0;
        blankCount = 0;
        blankX = 0;
        blankY = 0;
        if (board[0][0] == BLANK) {
            blankCount++;
            blankX = 0;
            blankY = 0;
        }
        if (board[0][0] == player) {
            oCount++;
        }
        if (board[1][1] == BLANK) {
            blankCount++;
            blankX = 1;
            blankY = 1;
        }
        if (board[1][1] == player) {
            oCount++;
        }
        if (board[2][2] == BLANK) {
            blankCount++;
            blankX = 2;
            blankY = 2;
        }
        if (board[2][2] == player) {
            oCount++;
        }
        if (oCount == 2 && blankCount == 1) {
            board[blankX][blankY] = O_MOVE;
            grid[blankX][blankY].setText("O");
            grid[blankX][blankY].setEnabled(false);
            return true;
        }
        //check top right to bottom left
        oCount = 0;
        blankCount = 0;
        blankX = 0;
        blankY = 0;
        if (board[2][0] == BLANK) {
            blankCount++;
            blankX = 2;
            blankY = 0;
        }
        if (board[0][0] == player) {
            oCount++;
        }
        if (board[1][1] == BLANK) {
            blankCount++;
            blankX = 1;
            blankY = 1;
        }
        if (board[1][1] == player) {
            oCount++;
        }
        if (board[0][2] == BLANK) {
            blankCount++;
            blankX = 0;
            blankY = 2;
        }
        if (board[0][2] == player) {
            oCount++;
        }
        if (oCount == 2 && blankCount == 1) {
            board[blankX][blankY] = O_MOVE;
            grid[blankX][blankY].setText("O");
            grid[blankX][blankY].setEnabled(false);
            return true;
        }
        return false;
    }
    public boolean checkWin(int player) {// 8 methods for checking wins
        if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {// top row
            combo = 1;
            return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {// top left to bottom right
            // diagonal
            combo = 2;
            return true;
        }
        if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {// middle row
            combo = 3;
            return true;
        }
        if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {// bottom row
            combo = 4;
            return true;
        }
        if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {// left column
            combo = 5;
            return true;
        }
        if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {// middle column
            combo = 6;
            return true;
        }
        if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {// right column
            combo = 7;
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {// top right to bottom left diagonal
            combo = 8;
            return true;
        }
        return false;
    }

    public boolean checkTie() {// checking for ties
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column] == BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
    public void setColor(int combo) {//Coloring squares for wins
        if (combo == 1) {
            grid[0][0].setTextColor(Color.GREEN);
            grid[0][1].setTextColor(Color.GREEN);
            grid[0][2].setTextColor(Color.GREEN);
        } else if (combo == 2) {
            grid[0][0].setTextColor(Color.GREEN);
            grid[1][1].setTextColor(Color.GREEN);
            grid[2][2].setTextColor(Color.GREEN);
        } else if (combo == 3) {
            grid[1][0].setTextColor(Color.GREEN);
            grid[1][1].setTextColor(Color.GREEN);
            grid[1][2].setTextColor(Color.GREEN);
        } else if (combo == 4) {
            grid[2][0].setTextColor(Color.GREEN);
            grid[2][1].setTextColor(Color.GREEN);
            grid[2][2].setTextColor(Color.GREEN);
        } else if (combo == 5) {
            grid[0][0].setTextColor(Color.GREEN);
            grid[1][0].setTextColor(Color.GREEN);
            grid[2][0].setTextColor(Color.GREEN);
        } else if (combo == 6) {
            grid[0][1].setTextColor(Color.GREEN);
            grid[1][1].setTextColor(Color.GREEN);
            grid[2][1].setTextColor(Color.GREEN);
        } else if (combo == 7) {
            grid[0][2].setTextColor(Color.GREEN);
            grid[1][2].setTextColor(Color.GREEN);
            grid[2][2].setTextColor(Color.GREEN);
        } else if (combo == 8) {
            grid[0][2].setTextColor(Color.GREEN);
            grid[1][1].setTextColor(Color.GREEN);
            grid[2][0].setTextColor(Color.GREEN);
        }
    }

    public void resetColor() {//resetting the text color
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y].setTextColor(Color.BLACK);
            }
        }
    }
}