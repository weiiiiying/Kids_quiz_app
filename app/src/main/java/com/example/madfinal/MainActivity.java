package com.example.madfinal;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mathButton;
    private Button englishButton;
    private TextView mathScoreTextView;
    private TextView englishScoreTextView;

    private int mathScore = 0;
    private int englishScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




                mathButton = findViewById(R.id.btn_math);
                englishButton = findViewById(R.id.btn_english);
                mathScoreTextView = findViewById(R.id.tv_math_score);
                englishScoreTextView = findViewById(R.id.tv_english_score);


                mathButton.setOnClickListener(v -> {
                    Intent intent = new Intent(this, MathActivity.class);
                    startActivityForResult(intent, MATH_REQUEST_CODE);
                });


                englishButton.setOnClickListener(v -> {
                    Intent intent = new Intent(this, EnglishActivity.class);
                    startActivityForResult(intent, ENGLISH_REQUEST_CODE);

                });
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == MATH_REQUEST_CODE && resultCode == RESULT_OK) {

                    mathScore = data.getIntExtra("score", 0);


                    mathScoreTextView.setText("Math Score: " + mathScore + " Star");
                } else if (requestCode == ENGLISH_REQUEST_CODE && resultCode == RESULT_OK) {

                    englishScore = data.getIntExtra("score", 0);


                    englishScoreTextView.setText("English Score: " + englishScore + " Star");
                }
            }

            private static final int MATH_REQUEST_CODE = 1;
            private static final int ENGLISH_REQUEST_CODE = 2;
        }
