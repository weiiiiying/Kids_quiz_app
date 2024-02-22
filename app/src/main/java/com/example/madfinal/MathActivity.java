package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MathActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button optionOneButton;
    private Button optionTwoButton;
    private Button submitButton, nextButton;

    private int correctAnswer;
    private int score;

    private int questionCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

                questionTextView = findViewById(R.id.tv_question_m);
                optionOneButton = findViewById(R.id.btn_option_one_math);
                optionTwoButton = findViewById(R.id.btn_option_two_math);
                submitButton = findViewById(R.id.btn_submit_math);
                nextButton = findViewById(R.id.btnNext);


                generateQuestion();

                submitButton.setOnClickListener(v -> {

                    Intent intent = new Intent();
                    intent.putExtra("score", score);
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }

            private void generateQuestion() {

                int num1 = (int) (Math.random() * 10) + 1;
                int num2 = (int) (Math.random() * 10) + 1;

                String operator = Math.random() > 0.5 ? "+" : "-";

                correctAnswer = operator.equals("+") ? num1 + num2 : num1 - num2;


                int incorrectAnswer;
                do {
                    incorrectAnswer = (int) (Math.random() * 20) + 1;
                } while (incorrectAnswer == correctAnswer);

                questionTextView.setText(num1 + " " + operator + " " + num2 + " = ?");
                optionOneButton.setText(String.valueOf(correctAnswer));
                optionTwoButton.setText(String.valueOf(incorrectAnswer));

                optionOneButton.setOnClickListener(v -> checkAnswer(Integer.parseInt(optionOneButton.getText().toString())));
                optionTwoButton.setOnClickListener(v -> checkAnswer(Integer.parseInt(optionTwoButton.getText().toString())));


                questionCounter++;
                if (questionCounter == 3) {
                    nextButton.setEnabled(false);
                    submitButton.setEnabled(true);
                }
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        generateQuestion();
                    }
                });


            }

            private void checkAnswer(int answer) {

                if (answer == correctAnswer) {

                    score++;

                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "Incorrect. The correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
                }


                submitButton.setVisibility(View.VISIBLE);


            }
        }

