package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean player1Active;
    TextView player1Score, player2Score, Status,player1,player2;

    private Button[] buttons = new Button[9];
    private Button resetbt;
    int[] gamestates = {0,0,0,0,0,0,0,0,0};
    int[][] winn = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int rounds;
    int player1ScoreCount,player2ScoreCount;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        player1Score = findViewById(R.id.player1_score);
        player2Score = findViewById(R.id.player2_score);
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        Status = findViewById(R.id.status);
        resetbt = findViewById(R.id.resetbt);
        player1ScoreCount=0;
        player2ScoreCount=0;
        Intent intent = getIntent();
        String playeronename = intent.getStringExtra("player1name");
        String playertwoname = intent.getStringExtra("player2name");
        player1.setText(playeronename);
        player2.setText(playertwoname);
        rounds = 0;
        player1Active=true;
        buttons[0] = findViewById(R.id.bt0);
        buttons[1] = findViewById(R.id.bt1);
        buttons[2] = findViewById(R.id.bt2);
        buttons[3] = findViewById(R.id.bt3);
        buttons[4] = findViewById(R.id.bt4);
        buttons[5] = findViewById(R.id.bt5);
        buttons[6] = findViewById(R.id.bt6);
        buttons[7] = findViewById(R.id.bt7);
        buttons[8] = findViewById(R.id.bt8);

        for(int i=0; i<buttons.length; i++)
        {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!((Button)view).getText().toString().equals(""))
                    {
                        return;
                    }
                    else if(checkWinner())
                    {
                        return;
                    }
                    String buttonid=view.getResources().getResourceEntryName(view.getId());
                    int gamestatepointer=Integer.parseInt(buttonid.substring(buttonid.length()-1,buttonid.length()));
                    if(player1Active)
                    {
                        ((Button)view).setText("X");
                        ((Button)view).setTextColor(Color.parseColor("#E61919"));
                        gamestates[gamestatepointer]=1;
                    }
                    else
                    {
                        ((Button)view).setText("O");
                        ((Button)view).setTextColor(Color.parseColor("#000000"));
                        gamestates[gamestatepointer]=2;
                    }
                    rounds++;
                    if(checkWinner())
                    {
                        if(player1Active)
                        {
                            player1ScoreCount++;
                            Status.setText(playeronename+" has won");
                            builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                                dialog.cancel();
                            });
                            builder.setPositiveButton("Play again", (DialogInterface.OnClickListener) (dialog, which) -> {
                                playAgain();
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.setMessage(playeronename+" has won");
                            alertDialog.setTitle("Game Over!");
                            alertDialog.show();
                          //  dialog.show();
                            updateScore();
                        }
                        else
                        {
                            player2ScoreCount++;
                            Status.setText(playertwoname+" has won");
                            builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                                dialog.cancel();
                            });
                            builder.setPositiveButton("Play again", (DialogInterface.OnClickListener) (dialog, which) -> {
                                playAgain();
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.setMessage(playertwoname+" has won");
                            alertDialog.setTitle("Game Over!");
                            alertDialog.show();
                            updateScore();
                        }
                    }
                    else if(rounds==9)
                    {
                        Status.setText("No Winner");
                        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                        });
                        builder.setPositiveButton("Play again", (DialogInterface.OnClickListener) (dialog, which) -> {
                            playAgain();
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.setMessage("No winner");
                        alertDialog.setTitle("Game Over!");
                        alertDialog.show();
                        updateScore();
                    }
                    else
                    {
                        player1Active=!player1Active;
                    }
                }

            });
        }
        resetbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                player1ScoreCount= 0;
                player2ScoreCount= 0;
                updateScore();
            }
        });
    }

    private boolean checkWinner()
    {
        boolean winnerplayer  = false;
        for (int[] winningpositions: winn)
        {
            if(gamestates[winningpositions[0]]==gamestates[winningpositions[1]]&& gamestates[winningpositions[1]]==gamestates[winningpositions[2]] && gamestates[winningpositions[0]]!=0)
            {
                winnerplayer = true;
            }
        }
        return winnerplayer;
    }

    private void playAgain()
    {
        rounds = 0;
        player1Active = true;
        for (int i=0; i<buttons.length; i++)
        {
            gamestates[i] = 0;
            buttons[i].setText("");
        }
        Status.setText("Status");
    }
    private void updateScore()
    {
        player1Score.setText(Integer.toString(player1ScoreCount));
        player2Score.setText(Integer.toString(player2ScoreCount));
    }

}