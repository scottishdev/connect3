package pcodeclan.com.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 9 spaces for gamestate
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;// 0: yellow, 1: red, 2: empty
    boolean gameActive = true;
    int gameTurns = 0;
/*    int p1Score = 0; // yellow player
    int p2Score = 0; // red player
    TextView p1TextView = (TextView) findViewById(R.id.p1TextView);
    TextView p2TextView = (TextView) findViewById(R.id.p2TextView);*/

    // Method for dropping counters in the square
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive && gameTurns != 9) { //Checks if counter has been tapped. If no, then place counter and continue game. Also, only runs if gameis active
            gameState[tappedCounter] = activePlayer; //changes the value of the current element in gameState to the activePlayer
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(800).setDuration(300);

            gameTurns ++;

            if (gameTurns == 9){
                // game ends on draw
                gameActive = false;
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText("It's a Draw!");

                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false; //Ends the game

                    //If activeplayer 0 (yellow) wins then activeplayer is set to 1 (red)
                    //So the messages are reversed. i.e. activeplayer 0 then red wins
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Red";
/*                        p2Score ++;
                        p2TextView.setText("Red: " + p2Score);*/
                    } else if (activePlayer == 1) {
                        winner = "Yellow";
/*                        p1Score ++;
                        p1TextView.setText("Yellow: " + p1Score);*/
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        } else { Toast.makeText(this, "Click on an empty space", Toast.LENGTH_SHORT).show(); //Else If user clicks on occupied tile
            }
    }

    public void playAgain(View view) {
        //Make playagain button and winnertext invisible
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        //set counter image views to empty
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout1);
        for (int i=0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        //update array gameState
/*        for (int element : gameState){
            gameState[element] = 2;
        }*/
        for (int i=0; i<gameState.length;i++){
            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true; //set game active to true.
        gameTurns = 0;
    }

/*    public void updatePointsText(){
        p1TextView.setText("Yellow: " + p1Score);
        p2TextView.setText("Red: " + p2Score);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}