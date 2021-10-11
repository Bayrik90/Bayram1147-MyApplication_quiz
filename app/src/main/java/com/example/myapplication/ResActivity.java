package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResActivity extends AppCompatActivity {
    private int trueAnswers;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trueAnswers=getIntent().getIntExtra("trueAnswers",0);
        answerTextView = findViewById(R.id.answerTextView);
        answerTextView.setText("Отображение правильных ответов: "+String.valueOf(trueAnswers));
    }
}