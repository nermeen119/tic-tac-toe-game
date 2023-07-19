package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class playername extends AppCompatActivity {
    ImageView image;
    Button start;
    EditText player1,player2;
    public String nameOfplayer1,nameOFplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playername);
        getSupportActionBar().hide();
        start=findViewById(R.id.startbt);
        player1=findViewById(R.id.player1name);
        player2=findViewById(R.id.player2name);
        nameOfplayer1=player1.getText().toString();
        nameOFplayer2=player2.getText().toString();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(playername.this,MainActivity.class));
                Intent i = new Intent(playername.this, MainActivity.class);
                i.putExtra("player1name", player1.getText().toString());
                i.putExtra("player2name", player2.getText().toString());
                startActivity(i);
            }
        });
    }
}