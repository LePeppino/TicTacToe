package de.lepeppino.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button f11, f21, f31, f12, f22, f32, f13, f23, f33;
    String xo = "X";
    int[][] gameStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f11 = findViewById(R.id.field_11);
        f21 = findViewById(R.id.field_21);
        f31 = findViewById(R.id.field_31);
        f12 = findViewById(R.id.field_12);
        f22 = findViewById(R.id.field_22);
        f32 = findViewById(R.id.field_32);
        f13 = findViewById(R.id.field_13);
        f23 = findViewById(R.id.field_23);
        f33 = findViewById(R.id.field_33);

        f11.setOnClickListener(this);
        f21.setOnClickListener(this);
        f31.setOnClickListener(this);
        f12.setOnClickListener(this);
        f22.setOnClickListener(this);
        f32.setOnClickListener(this);
        f13.setOnClickListener(this);
        f23.setOnClickListener(this);
        f33.setOnClickListener(this);

        gameStorage = new int[3][3];
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.field_11:
                f11.setText(xo);
                handleInput(1, 1);
                break;
            case R.id.field_21:
                f21.setText(xo);
                handleInput(2, 1);
                break;
            case R.id.field_31:
                f31.setText(xo);
                handleInput(3, 1);
                break;
            case R.id.field_12:
                f12.setText(xo);
                handleInput(1, 2);
                break;
            case R.id.field_22:
                f22.setText(xo);
                handleInput(2, 2);
                break;
            case R.id.field_32:
                f32.setText(xo);
                handleInput(3, 2);
                break;
            case R.id.field_13:
                f13.setText(xo);
                handleInput(1, 3);
                break;
            case R.id.field_23:
                f23.setText(xo);
                handleInput(2, 3);
                break;
            case R.id.field_33:
                f33.setText(xo);
                handleInput(3, 3);
                break;
        }
    }

    private void handleInput(int x, int y){
        if(gameStorage[x - 1][y - 1] == 0){
            if (xo.equals("X")) {
                gameStorage[x - 1][y - 1] = 1;
                xo = "O";
            } else{
                gameStorage[x - 1][y - 1] = -1;
                xo = "X";
            }
        }
        if(checkGameTied()){
            Toast.makeText(getApplicationContext(), "Unentschieden!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }else if(checkGameEnd()){
            finishGame();
        }
    }

    private boolean checkGameTied(){
        return !checkGameEnd()
                && gameStorage[0][0] != 0
                && gameStorage[0][1] != 0
                && gameStorage[0][2] != 0
                && gameStorage[1][0] != 0
                && gameStorage[1][1] != 0
                && gameStorage[1][2] != 0
                && gameStorage[2][0] != 0
                && gameStorage[2][1] != 0
                && gameStorage[2][2] != 0;
    }
    private boolean checkGameEnd(){
        return  (Math.abs(gameStorage[0][0] + gameStorage[0][1] + gameStorage[0][2]) == 3
                    || Math.abs(gameStorage[1][0] + gameStorage[1][1] + gameStorage[1][2]) == 3
                    || Math.abs(gameStorage[2][0] + gameStorage[2][1] + gameStorage[2][2]) == 3
                    || Math.abs(gameStorage[0][0] + gameStorage[1][0] + gameStorage[2][0]) == 3
                    || Math.abs(gameStorage[0][1] + gameStorage[1][1] + gameStorage[2][1]) == 3
                    || Math.abs(gameStorage[0][2] + gameStorage[1][2] + gameStorage[2][2]) == 3
                    || Math.abs(gameStorage[0][0] + gameStorage[1][1] + gameStorage[2][2]) == 3
                    || Math.abs(gameStorage[0][2] + gameStorage[1][1] + gameStorage[2][0]) == 3);
    }

    private void finishGame(){
        if (xo.equals("X")) {
            Toast.makeText(getApplicationContext(), "O gewinnt!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "X gewinnt!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
