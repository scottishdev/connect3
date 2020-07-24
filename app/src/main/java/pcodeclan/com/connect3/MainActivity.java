package pcodeclan.com.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int [] gameState = {2,2,2,2,2,2,2,2,2}; // 9 spaces for gamestate
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;// 0: yellow, 1: red, 2: empty

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        Log.i("tag", (String)counter.getTag());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameState[tappedCounter] = activePlayer; //changes the value of the current element in gameState to the activePlayer

        counter.setTranslationY(-1500);

        if (activePlayer == 0){
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotation(800).setDuration(300);

        for (int [] winningPosition : winningPositions){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                //If activeplayer 0 (yellow) wins then activeplayer is set to 1 (red)
                //So the messages are reversed. i.e. activeplayer 0 then red wins
                String winner;
                if (activePlayer == 0){
                    winner = "Red";
                    Log.i("won", winner + " has won");
                } else if (activePlayer == 1){
                    winner = "Yellow";
                    Log.i("won", winner + " has won");
                }


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}