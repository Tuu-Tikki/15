package com.example.a15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //values of stones
        final int[] stonesNumber = {R.integer.stone_1,  R.integer.stone_2,  R.integer.stone_3,  R.integer.stone_4,
                                    R.integer.stone_5,  R.integer.stone_6,  R.integer.stone_7,  R.integer.stone_8,
                                    R.integer.stone_9,  R.integer.stone_10, R.integer.stone_11, R.integer.stone_12,
                                    R.integer.stone_13, R.integer.stone_14, R.integer.stone_15, R.integer.empty};
        //layout's objects for the stones
        final TextView[] gameField =
                        {findViewById(R.id.number_1), findViewById(R.id.number_2), findViewById(R.id.number_3), findViewById(R.id.number_4),
                        findViewById(R.id.number_5), findViewById(R.id.number_6), findViewById(R.id.number_7), findViewById(R.id.number_8),
                        findViewById(R.id.number_9), findViewById(R.id.number_10), findViewById(R.id.number_11), findViewById(R.id.number_12),
                        findViewById(R.id.number_13), findViewById(R.id.number_14), findViewById(R.id.number_15), findViewById(R.id.number_16)};

        //set game field before the game's start
        for (int i=0; i < 16; i++) {
            gameField[i].setText(stonesNumber[i]);
        };
         gameField[15].setVisibility(View.INVISIBLE);

         //shuffle (test)
        Button check = findViewById(R.id.checkButton);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGameField(gameField, stonesNumber);
                boolean answer = checkSolvability(gameField, stonesNumber);
                TextView showAnswer = findViewById(R.id.answer);
                showAnswer.setText(String.valueOf(answer));
            }
        });

        //move the stone in the available free space by click
        for (int i=0; i < 16; i++) {
            final int counter = i;
            gameField[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int empty = checkNeighbor(counter, gameField, stonesNumber);
                    if (empty > -1) {
                        CharSequence value = gameField[counter].getText();
                        gameField[counter].setText(stonesNumber[15]);
                        gameField[counter].setVisibility(View.INVISIBLE);
                        gameField[empty].setText(value);
                        gameField[empty].setVisibility(View.VISIBLE);
                    }
                }
            });
        };
    }

    //to find the empty slot nearby the clicked stone
    private int checkNeighbor (int i, TextView[] gameField, int[] stonesNumber) {
        int empty = -1;
        if ( i - 4 > -1) {
            if (Integer.parseInt(gameField[i-4].getText().toString()) == getResources().getInteger(stonesNumber[15])) {
                empty = i - 4;
                return empty;
            }
        }
        if ( i - 1 > -1) {
            if (Integer.parseInt(gameField[i-1].getText().toString()) == getResources().getInteger(stonesNumber[15])) {
                empty = i - 1;
                return empty;
            }
        }
        if ( i + 1 < 16) {
            if (Integer.parseInt(gameField[i+1].getText().toString()) == getResources().getInteger(stonesNumber[15])) {
                empty = i + 1;
                return empty;
            }
        }
        if ( i + 4 < 16) {
            if (Integer.parseInt(gameField[i+4].getText().toString()) == getResources().getInteger(stonesNumber[15])) {
                empty = i + 4;
                return empty;
            }
        }
        return empty;
    }

    //shuffle the stones to start the game
    private void setGameField(TextView[] gameField, int[] stonesNumber) {
        Random r = new Random();
        int random = 0;
        int empty = 0;
        int[] values = new int[16];

        do {
            values = stonesNumber.clone();
            for (int i = 0; i < 16; i++) {
                random = r.nextInt(16 - i);
                gameField[i].setText(values[random]);
                for (int k = random; k < 15 - i; k++) {
                    values[k] = values[k + 1];
                }
            }
        } while (!checkSolvability(gameField, stonesNumber));

        for (int i = 0; i < 16; i++) {
            gameField[i].setVisibility(View.VISIBLE);
        }
        while(Integer.parseInt(gameField[empty].getText().toString()) != getResources().getInteger(stonesNumber[15])) {
            empty++;
        };
        gameField[empty].setVisibility(View.INVISIBLE);
    }

    //to check if the stone set has a solve
    private boolean checkSolvability (TextView[] gameField, int[] stonesNumber) {
        boolean solvability = false;
        int inversionsNumber = 0;
        int blankPosition = 0;
        int blankRow = 0;
        int[] row = new int[15];
        boolean oddEvenInversions = false;

        //find the blank/empty/ slot and the row number for it
        while(Integer.parseInt(gameField[blankPosition].getText().toString()) != getResources().getInteger(stonesNumber[15])) {
            blankPosition++;
        };
        blankRow = blankPosition/(int)4 + 1;
        //write all stone's values (without 0 for blank/empty/ slot) in one row to find inversions
        for (int i = 0; i < blankPosition; i++) {
            row[i] = Integer.parseInt(gameField[i].getText().toString());
        }
        for (int i = blankPosition; i < 15; i++) {
            row[i]=Integer.parseInt(gameField[i+1].getText().toString());
        }
        //find all inversions and find out if their number is odd or even
        for (int i = 0; i < 14; i++) {
            for (int k = i + 1; k < 15; k++) {
                if (row[i] > row[k]) {
                    inversionsNumber++;
                }
            }
        }
        if (inversionsNumber%2 == 0) {
            //even
            oddEvenInversions = true;
        } else {
            //odd
            oddEvenInversions = false;
        }
        //check solvability
        if(oddEvenInversions){
            if (blankRow == 2 || blankRow == 4) {
                solvability = true;
            } else {solvability = false;}
        } else {
            if (blankRow == 1 || blankRow == 3) {
                solvability = true;
            } else {solvability = false;}
        }

        return solvability;
    }
}



