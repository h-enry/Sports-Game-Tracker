package com.example.pingpongscoringapp;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int p1_score = 0;
    int p2_score = 0;
    String winner;

public void player1_addPoint(View view) {
    p1_score += 1;
    p1_display(p1_score);
    if (p1_score == 11)
        winner = "P1 WINS!";
}

public void player2_addPoint(View view) {
    p2_score += 1;
    p2_display(p2_score);
    if (p2_score == 11)
        winner = "P2 WINS!";
}
public void player1_minusPoint(View view){
    p1_score -= 1;
    p1_display(p1_score);
}
public void player2_minusPoint(View view){
    p2_score -= 1;
    p2_display(p2_score);
}
public void reset_score(View view){
    p1_score = 0;
    p2_score = 0;
    p1_display(p1_score);
    p2_display(p2_score);
}
public void p1_display(int score) {
    TextView scoreView = (TextView) findViewById(R.id.p1_score);
    scoreView.setText(String.valueOf(score));
}
public void p2_display(int score) {
    TextView scoreView = (TextView) findViewById(R.id.p2_score);
    scoreView.setText(String.valueOf(score));
}
public void winner_display(String winner) {
    TextView winnerView = (TextView) findViewById(R.id.winner);
    winnerView.setText(String.valueOf(winner));
}
}
