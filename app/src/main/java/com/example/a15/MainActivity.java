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

        final int[] number = {R.string.stone_1,  R.string.stone_2,  R.string.stone_3,  R.string.stone_4,
                              R.string.stone_5,  R.string.stone_6,  R.string.stone_7,  R.string.stone_8,
                              R.string.stone_9,  R.string.stone_10, R.string.stone_11, R.string.stone_12,
                              R.string.stone_13, R.string.stone_14, R.string.stone_15, R.string.empty};

        final TextView[] gameField =
                        {findViewById(R.id.number_1), findViewById(R.id.number_2), findViewById(R.id.number_3), findViewById(R.id.number_4),
                        findViewById(R.id.number_5), findViewById(R.id.number_6), findViewById(R.id.number_7), findViewById(R.id.number_8),
                        findViewById(R.id.number_9), findViewById(R.id.number_10), findViewById(R.id.number_11), findViewById(R.id.number_12),
                        findViewById(R.id.number_13), findViewById(R.id.number_14), findViewById(R.id.number_15), findViewById(R.id.number_16)};

        for (int i=0; i < 16; i++) {
            gameField[i].setText(number[i]);
        };

        for (int i=0; i < 16; i++) {
            final int counter = i;
            gameField[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int empty = checkNeighbor(counter, gameField);
                    if (empty > -1) {
                        //int value = Integer.parseInt(gameField[counter].getText().toString());
                        CharSequence value=gameField[counter].getText();
                        gameField[counter].setText(R.string.empty);
                        gameField[empty].setText(value);
                    }
                }
            });
        };
    }

    private int checkNeighbor (int i, TextView[] gameField) {
        int empty = -1;
        if ( i - 4 > 0) {
            if (gameField[i-4].getText().toString().equals(R.string.empty)) {
                empty = i - 4;
                return empty;
            }
        }
        if ( i - 1 > 0) {
            if (gameField[i-1].getText().toString().equals(R.string.empty)) {
                empty = i - 1;
                return empty;
            }
        }
        if ( i + 1 < 16) {
            if (gameField[i+1].getText().toString().equals(R.string.empty)) {
                empty = i + 1;
                return empty;
            }
        }
        if ( i + 4 < 16) {
            if (gameField[i+4].getText().toString().equals(R.string.empty)) {
                empty = i + 4;
                return empty;
            }
        }
        return empty;
    }
}



