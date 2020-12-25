package com.example.a15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] number = {R.integer.stone_1,  R.integer.stone_2,  R.integer.stone_3,  R.integer.stone_4,
                              R.integer.stone_5,  R.integer.stone_6,  R.integer.stone_7,  R.integer.stone_8,
                              R.integer.stone_9,  R.integer.stone_10, R.integer.stone_11, R.integer.stone_12,
                              R.integer.stone_13, R.integer.stone_14, R.integer.stone_15, R.integer.empty};

        final TextView[] gameField =
                        {findViewById(R.id.number_1), findViewById(R.id.number_2), findViewById(R.id.number_3), findViewById(R.id.number_4),
                        findViewById(R.id.number_5), findViewById(R.id.number_6), findViewById(R.id.number_7), findViewById(R.id.number_8),
                        findViewById(R.id.number_9), findViewById(R.id.number_10), findViewById(R.id.number_11), findViewById(R.id.number_12),
                        findViewById(R.id.number_13), findViewById(R.id.number_14), findViewById(R.id.number_15), findViewById(R.id.number_16)};

        //set game field for the first  time
        for (int i=0; i < 16; i++) {
            gameField[i].setText(number[i]);
        };
         gameField[15].setVisibility(View.INVISIBLE);

        //move the stone in the available free space
        for (int i=0; i < 16; i++) {
            final int counter = i;
            gameField[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int empty = checkNeighbor(counter, gameField);
                    if (empty > -1) {
                        //int value = Integer.parseInt(gameField[counter].getText().toString());
                        CharSequence value = gameField[counter].getText();
                        gameField[counter].setText("0");
                        gameField[counter].setVisibility(View.INVISIBLE);
                        gameField[empty].setText(value);
                        gameField[empty].setVisibility(View.VISIBLE);
                    }
                }
            });
        };
    }

    private int checkNeighbor (int i, TextView[] gameField) {
        int empty = -1;
        if ( i - 4 > 0) {
            if (Integer.parseInt(gameField[i-4].getText().toString()) == 0) {
                empty = i - 4;
                return empty;
            }
        }
        if ( i - 1 > 0) {
            if (Integer.parseInt(gameField[i-1].getText().toString()) == 0) {
                empty = i - 1;
                return empty;
            }
        }
        if ( i + 1 < 16) {
            if (Integer.parseInt(gameField[i+1].getText().toString()) == 0) {
                empty = i + 1;
                return empty;
            }
        }
        if ( i + 4 < 16) {
            if (Integer.parseInt(gameField[i+4].getText().toString()) == 0) {
                empty = i + 4;
                return empty;
            }
        }
        return empty;
    }
}



