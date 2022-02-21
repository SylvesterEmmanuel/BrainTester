package com.example.briantester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ConstraintLayout gameLayout;
    TextView sumTextView;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions;
    TextView resultTextView;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    Button playAgainButton;
    TextView scoreTextView;
    boolean gameActive = true;


    public void start (View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);


    }
    public void chooseGame(View view){
        if ( Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestions();
    }
    public void newQuestions(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i = 0;i < 4;i++){
            if ( locationOfCorrectAnswer == i){
                answers.add(a + b);
            }else{
                int wrongAnswer = random.nextInt(41);
                while ( wrongAnswer == a + b ){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        score = 0;
        newQuestions();
        gameActive = true;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        countDownTimer = new CountDownTimer(30100,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameActive = true;
                timerTextView.setText(String.valueOf(millisUntilFinished /1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Done!");
                gameActive = false;
                Toast.makeText(MainActivity.this, "Game over", Toast.LENGTH_SHORT).show();

            }
        }.start();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        gameLayout =  findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        scoreTextView =(TextView) findViewById(R.id.scoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
}