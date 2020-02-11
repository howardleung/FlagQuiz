package com.example.a324008911.flagquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import java.text.NumberFormat;
import java.util.Random;

import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class FlagQuiz extends Activity {
    private ImageView flag;
    private TextView pointsValue;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private String answer;

    private Random r = new Random();
    private int question = 1;
    private String firstText;
    private String secondText;
    private String thirdText;
    private String fourthText;


    private String[] names = {"Canada","Germany","France","Italy","Hong Kong","South Korea","North Korea","Egypt","Hungary","Brazil"};
    private String[] namesLeft = {"Canada","Germany","France","Italy","Hong Kong","South Korea","North Korea","Egypt","Hungary","Brazil"};

    private SharedPreferences savedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_quiz);
        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);
        answerFour = findViewById(R.id.answerFour);
        pointsValue = findViewById(R.id.pointsValue);
        flag = findViewById(R.id.flag);


        flag.setImageDrawable(getResources().getDrawable(R.drawable.germany));
        newQuestion();
    }


    private void newQuestion() {
        int correct=r.nextInt(10);
        answer = namesLeft[correct];
        int randomNum;
        int randomNum2;
        int randomNum3;
        int randomNum4;
        do {
            randomNum = r.nextInt(10);
            do {
                randomNum2 = r.nextInt(10);
            } while (!(randomNum2 != randomNum));
            do {
                randomNum3 = r.nextInt(10);
            } while (!(randomNum3 != randomNum && randomNum3 != randomNum2  ));
            do {
                randomNum4 = r.nextInt(10);
            } while (!(randomNum4 != randomNum && randomNum4 != randomNum2 && randomNum4 != randomNum3 ));

        }while (!(names[randomNum].equals(namesLeft[correct]) || names[randomNum2].equals(namesLeft[correct]) ||names[randomNum3].equals(namesLeft[correct])||names[randomNum4].equals(namesLeft[correct])));


        answerOne.setText(names[randomNum]);
        answerTwo.setText(names[randomNum2]);
        answerThree.setText(names[randomNum3]);
        answerFour.setText(names[randomNum4]);
    }


    private void check() {






    }




}
