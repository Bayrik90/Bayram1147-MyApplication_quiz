package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button yesBtn;//1
    Button noBtn;//4
    Button getAnswer;
    ArrayList checkQuestions=new ArrayList();
    TextView textView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, false)
    };
    int questionIndex = 0;
    String resultAnswers=new String();
    int trueAnswers=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","onCreate() called");
        if(savedInstanceState !=null){
            questionIndex=savedInstanceState.getInt("questionIndex",0);
        }
        yesBtn=findViewById(R.id.yesBtn);//2 R-это папка ресурс,в кот находим id и через него выходим на на йесбтн
        noBtn=findViewById(R.id.noBtn);//5
        textView = findViewById(R.id.textView);
        getAnswer=findViewById(R.id.getAnswer);
        textView.setText(questions[questionIndex].getQuestionText());

        yesBtn.setOnClickListener(new View.OnClickListener() {//3 при клике будет появ-ся след метод:
            @Override
            public void onClick(View view) {//3 показ уведомления системы
                checkAnswer(true);
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        getAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });
    }
    public void checkAnswer(boolean btn){
        //Ответ правильный если нажата кнопка и ответ да, или нажата кнопка нет и ответ нет
        if((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue() && !btn))
        {Toast.makeText(MainActivity.this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
            trueAnswers++;
            String resultAnswers=getString(questions[questionIndex].getQuestionText());
            checkQuestions.add(resultAnswers+" Правильный");}
        else{
            Toast.makeText(MainActivity.this, R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
            String resultAnswers=getString(questions[questionIndex].getQuestionText());
            checkQuestions.add(resultAnswers+" Неравильный");
        }
        questionIndex = (questionIndex+1)%questions.length;
        textView.setText(questions[questionIndex].getQuestionText());

        if(questionIndex==questions.length-1){
            Intent intent1=new Intent(MainActivity.this,ResActivity.class);
            intent1.putExtra("trueAnswers",trueAnswers);
            startActivity(intent1);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("questionIndex",questionIndex);
    }
}