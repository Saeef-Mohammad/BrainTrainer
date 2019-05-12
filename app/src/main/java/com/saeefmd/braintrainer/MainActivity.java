package com.saeefmd.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;

    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answers = new ArrayList<>();

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    public void answerButtonEnabled(boolean flag) {

        button0.setEnabled(flag);
        button1.setEnabled(flag);
        button2.setEnabled(flag);
        button3.setEnabled(flag);

    }


    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        answerButtonEnabled(true);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");

                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                playAgainButton.setVisibility(View.VISIBLE);

                answerButtonEnabled(false);

            }
        }.start();

    }


    public void generateQuestion() {

        Random rand = new Random();

        int num1 = rand.nextInt(21) + 1;
        int num2 = rand.nextInt(21) + 1;

        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if(i == locationOfCorrectAnswer) {

                answers.add(num1 + num2);

            } else {

                incorrectAnswer = rand.nextInt(41) + 1;

                while (incorrectAnswer == num1 + num2) {

                    incorrectAnswer = rand.nextInt(41) + 1;

                }

                answers.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;

            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }

        numberOfQuestions++;

        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);

        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);

        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);


    }
}
