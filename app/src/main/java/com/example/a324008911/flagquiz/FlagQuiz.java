package com.example.a324008911.flagquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ProgressBar;
import java.text.NumberFormat;
import java.util.Random;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// Author: Howard Leung
// Date: May 29th, 2018
// Description: A flag quiz that prompts the user to guess the answer corresponding to a picture.
public class FlagQuiz extends Activity {
    private ImageView flag;
    private TextView pointsValue;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private String answer;
    private int correct;
    private Random r = new Random();
    private int question = 5;
    private int points = 0;
    private String firstText;
    private String secondText;
    private String thirdText;
    private String fourthText;
    private ProgressBar progressBar;

    private String[] names = {"Canada", "Germany", "France", "Italy", "Hong Kong", "South Korea", "Singapore", "Egypt", "Hungary", "Brazil"};
    private ArrayList<String> namesLeft = new ArrayList<>(10);


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
        progressBar = findViewById(R.id.progressBar);

        OnClickListener buttonEventListener = new ButtonListener();
        answerOne.setOnClickListener(buttonEventListener);
        answerTwo.setOnClickListener(buttonEventListener);
        answerThree.setOnClickListener(buttonEventListener);
        answerFour.setOnClickListener(buttonEventListener);

        //create namesLeft list
        namesLeft.add("Canada");
        namesLeft.add("Germany");
        namesLeft.add("France");
        namesLeft.add("Italy");
        namesLeft.add("Hong Kong");
        namesLeft.add("South Korea");
        namesLeft.add("Singapore");
        namesLeft.add("Egypt");
        namesLeft.add("Hungary");
        namesLeft.add("Brazil");


        flag.setImageDrawable(getResources().getDrawable(R.drawable.egypt));

        savedPrefs = getSharedPreferences( "TipCalcPrefs", MODE_PRIVATE );

        newQuestion();
    }


    private void newQuestion() {

        if (namesLeft.size()>1)
        correct = r.nextInt(namesLeft.size());
        else
            correct = 0;
        answer = namesLeft.get(correct);
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

            } while (!(randomNum3 != randomNum && randomNum3 != randomNum2));
            do {
                randomNum4 = r.nextInt(10);

            }
            while (!(randomNum4 != randomNum && randomNum4 != randomNum2 && randomNum4 != randomNum3));

        }
        while (!(names[randomNum].equals(answer) || names[randomNum2].equals(answer) || names[randomNum3].equals(answer) || names[randomNum4].equals(answer)));

        firstText = names[randomNum];
        secondText = names[randomNum2];
        thirdText = names[randomNum3];
        fourthText = names[randomNum4];

        answerOne.setText(firstText);
        answerTwo.setText(secondText);
        answerThree.setText(thirdText);
        answerFour.setText(fourthText);
        changeFlag(answer);

    }

    private void changeFlag(String country) {
        if (country.equals("Canada"))
        flag.setImageDrawable(getResources().getDrawable(R.drawable.canada));
        else if (country.equals("Germany"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.germany));
        else if (country.equals("France"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.france));
        else if (country.equals("Italy"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.italy));
        else if (country.equals("Hong Kong"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.hongkong));
        else if (country.equals("South Korea"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.southkorea));
        else if (country.equals("Singapore"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.singapore));
        else if (country.equals("Egypt"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.egypt));
        else if (country.equals("Hungary"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.hungary));
        else if (country.equals("Brazil"))
            flag.setImageDrawable(getResources().getDrawable(R.drawable.brazil));

    }


    private boolean check(String answerUser) {
        return answer.equals(answerUser);
    }


    private void correct() {
        points++;
        question+= 9;
        namesLeft.remove(correct);
        pointsValue.setText(String.valueOf(points));
        progressBar.setProgress(question);
    }

    private void incorrect() {
        points--;
        question+=9;
        namesLeft.remove(correct);
        pointsValue.setText(String.valueOf(points));
        progressBar.setProgress(question);
    }


    private void endgame() {

        answerOne.setText("You");
        answerTwo.setText("Scored");
        if (points>4){
            flag.setImageDrawable(getResources().getDrawable(R.drawable.victory));

            answerThree.setText("50%");
            answerFour.setText("Or above!");
        }if (points<5){
            flag.setImageDrawable(getResources().getDrawable(R.drawable.defeat));

            answerThree.setText("below");
            answerFour.setText("50%!");
        }

        answerOne.setOnClickListener(null);
        answerTwo.setOnClickListener(null);
        answerThree.setOnClickListener(null);
        answerFour.setOnClickListener(null);


    }

    class ButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            //answer one clicked
            if (v.getId() == R.id.answerOne) {
                if (check(firstText)) {
                    correct();
                }else
                    incorrect();

            } else if (v.getId() == R.id.answerTwo) {
                //answer two clicked
                if (check(secondText)) {
                    correct();
                }else
                incorrect();

            } else if (v.getId() == R.id.answerThree) {
                //answer three clicked
                if (check(thirdText)) {
                    correct();
                }else
                    incorrect();

            } else if (v.getId() == R.id.answerFour) {
                //answer four clicked
                if (check(fourthText)) {
                    correct();
                } else
                    incorrect();

            }
            if (question == 95) {
                endgame();
                return;
            }

            Log.e(String.valueOf(question),"end");
                newQuestion();

        }
    }
    @Override
    public void onPause() {
        // Save the question String variable
        Editor prefsEditor = savedPrefs.edit();
        prefsEditor.putString( "one", firstText );
        prefsEditor.putString( "two", secondText);
        prefsEditor.putString( "three", thirdText );
        prefsEditor.putString( "four", fourthText);
        prefsEditor.putString( "answer", answer);

        prefsEditor.commit();

        // Calling the parent onPause() must be done LAST
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load the instance variables back (or default values)
        firstText = savedPrefs.getString("one", firstText);
        secondText = savedPrefs.getString("two", secondText );

        thirdText = savedPrefs.getString("three", thirdText);
        fourthText = savedPrefs.getString("four", fourthText );
        answer = savedPrefs.getString("answer", answer);


        // Format/update these values in our widgets

        answerOne.setText(firstText);
        answerTwo.setText(secondText);
        answerThree.setText(thirdText);
        answerFour.setText(fourthText);
        changeFlag(answer);
    }
}



