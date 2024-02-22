package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EnglishActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button optionOneButton;
    private Button optionTwoButton;
    private Button submitButton;

    private Button nextButton;

    private String correctAnswer;
    private int score;

    private View colorImageView;

    private int questionCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

                questionTextView = findViewById(R.id.tv_question);
                optionOneButton = findViewById(R.id.btn_option_one);
                optionTwoButton = findViewById(R.id.btn_option_two);
                submitButton = findViewById(R.id.btn_submit);
                colorImageView = findViewById(R.id.color_image_view);
                nextButton = findViewById(R.id.BtnNext_eng);



                    generateQuestion();


                submitButton.setOnClickListener(v -> {
                    // Send the score back to the main activity
                    Intent intent = new Intent();
                    intent.putExtra("score", score);
                    setResult(RESULT_OK, intent);
                    finish();
                });
            }

            private void generateQuestion() {

                String[] colorNames = getResources().getStringArray(R.array.color_names);



                int randomColorIndex = (int) (Math.random() * colorNames.length);
                String colorName = colorNames[randomColorIndex];


                String incorrectAnswer;
                do {
                    incorrectAnswer = colorNames[(int) (Math.random() * colorNames.length)];
                } while (incorrectAnswer.equals(colorName));


                questionTextView.setText("What color is this?");
                optionOneButton.setText(colorName);
                if(colorName.equals("BLUE")){
                    colorImageView.setBackgroundResource(R.drawable.blue);
                }
                if(colorName.equals("RED")){
                    colorImageView.setBackgroundResource(R.drawable.red);
                }
                optionTwoButton.setText(incorrectAnswer);
//                String colorid = "@color/" + colorName;
////                String colorid = colorName;
//                Drawable drawable = getResources().getDrawable(getResources().getIdentifier(colorid.substring(1), "drawable", getPackageName()));
//                colorImageView.setBackground(drawable);


                optionOneButton.setOnClickListener(v -> checkAnswer(optionOneButton.getText().toString()));
                optionTwoButton.setOnClickListener(v -> checkAnswer(optionTwoButton.getText().toString()));

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

                correctAnswer = colorName;
            }

            private void checkAnswer(String answer) {

                if (answer.equals(correctAnswer)) {

                    score++;

                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "Incorrect. The correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
                }




                submitButton.setVisibility(View.VISIBLE);
            }
        }

