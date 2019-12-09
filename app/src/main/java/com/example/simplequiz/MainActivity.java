package com.example.simplequiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    RadioButton[] radioButtons = new RadioButton[5];
    RadioGroup radioGroup;
    Switch aSwitch;
    ToggleButton toggleButton;
    TextView questionTv;
    Button submit;
    CheckBox[] checkBoxes = new CheckBox[4];
    LinearLayout checkGroup;
    int currentQuestion = 0;
    int correctAnswers = 0;
    int totalQuestions = 6;
    Button clearBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkGroup = findViewById(R.id.checkGroup);
        radioGroup = findViewById(R.id.radioGroup);
        for(int i = 0; i < 5; i++){
            radioButtons[i] = radioGroup.findViewWithTag(i+"");
        }

        aSwitch = findViewById(R.id.switchButton);
        toggleButton = findViewById(R.id.toggle);
        questionTv = findViewById(R.id.question);
        clearBt = findViewById(R.id.clearButton);
        submit = findViewById(R.id.submitButton);

        clearBt.setOnClickListener(v ->{
            aSwitch.setChecked(false);
            toggleButton.setChecked(false);
            radioGroup.clearCheck();
            try{
            for(CheckBox c : checkBoxes){
                c.setChecked(false);
            }}catch (Exception e){
                Log.d("asd","don't care");
            }
        });
        showQuestion();

    }

    private void showQuestion() {
        if (currentQuestion == 0) {
            questionTv.setText("SINGLE CHOICE QUESTION!");
            radioButtons[0].setText("OPTION A");
            radioButtons[1].setText("OPTION A");
            radioButtons[2].setText("OPTION A");
            radioButtons[3].setText("OPTION A");
            radioButtons[4].setText("OPTION A");

            radioButtons[0].setSelected(true);
            radioGroup.setVisibility(View.VISIBLE);
            final int correct_answer = 1; //set correct answer
            submit.setOnClickListener(v -> {
                radioGroup.setVisibility(View.GONE);
                if (radioGroup.getCheckedRadioButtonId() == radioButtons[correct_answer].getId()) {
                    correctAnswers++;
                }
                currentQuestion++;
                radioGroup.clearCheck();
                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();

            });
        } else if (currentQuestion == 1) {
            questionTv.setText("SINGLE CHOICE QUESTION two!");
            radioButtons[0].setSelected(true);

            radioButtons[0].setText("OPTION A");
            radioButtons[1].setText("OPTION A");
            radioButtons[2].setText("OPTION A");
            radioButtons[3].setText("OPTION A");
            radioButtons[4].setText("OPTION A");
            radioGroup.setVisibility(View.VISIBLE);
            final int correct_answer = 2; //set correct answer
            submit.setOnClickListener(v -> {
                radioGroup.setVisibility(View.GONE);
                if (radioGroup.getCheckedRadioButtonId() == radioButtons[correct_answer].getId()) {
                    correctAnswers++;
                }
                currentQuestion++;
                radioGroup.clearCheck();

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if (currentQuestion == 2) {
            questionTv.setText("Question text toggle"); //todo
            toggleButton.setVisibility(View.VISIBLE);
            boolean correctSelection = false; //todo

            submit.setOnClickListener(v -> {

                toggleButton.setVisibility(View.GONE);
                if (v.isSelected() == correctSelection) {
                    correctAnswers++;
                }
                currentQuestion++;

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if(currentQuestion == 3){
            questionTv.setText("SWITCH YES NO QUESTION");
            aSwitch.setVisibility(View.VISIBLE);
            boolean correctSelection = false;
            submit.setOnClickListener(v -> {
                aSwitch.setVisibility(View.GONE);
                if (v.isSelected() == correctSelection) {
                    correctAnswers++;
                }
                currentQuestion++;

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if(currentQuestion == 4){
            checkGroup.setVisibility(View.VISIBLE);
            questionTv.setText("MULTIPLE CHIUSE ONE");
            for(int i = 0; i < 4; i++){
                checkBoxes[i] = checkGroup.findViewWithTag(i+"");
                checkBoxes[i].setChecked(false);
            }
            checkBoxes[0].setText("OPTION A");
            checkBoxes[1].setText("OPTION b");
            checkBoxes[2].setText("OPTION c");
            checkBoxes[3].setText("OPTION d");
            boolean[] correctOptions = new boolean[]{false,false,true,false};
            submit.setOnClickListener(v -> {
                checkGroup.setVisibility(View.GONE);
                boolean correct = true;
                for(int i = 0; i < 4; i++){
                    if (checkBoxes[i].isChecked()!= correctOptions[i]) {
                        correct = false;
                        break;
                    }
                }
                if(correct) correctAnswers++;
                currentQuestion++;

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });

        }else if(currentQuestion == 5){
            checkGroup.setVisibility(View.VISIBLE);
            questionTv.setText("MULTIPLE CHIUSE TWO");
            for(int i = 0; i < 4; i++){
                checkBoxes[i] = checkGroup.findViewWithTag(i+"");
                checkBoxes[i].setChecked(false);
            }
            checkBoxes[0].setText("OPTION A");
            checkBoxes[1].setText("OPTION b");
            checkBoxes[2].setText("OPTION c");
            checkBoxes[3].setText("OPTION d");
            boolean[] correctOptions = new boolean[]{false,true,true,false};
            submit.setOnClickListener(v -> {
                checkGroup.setVisibility(View.GONE);
                boolean correct = true;
                for(int i = 0; i < 4; i++){
                    if (checkBoxes[i].isChecked()!= correctOptions[i]) {
                        correct = false;
                        break;
                    }
                }
                if(correct) correctAnswers++;
                currentQuestion++;

                if (currentQuestion >= totalQuestions) {
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                    questionTv.setText("QUIZ FINISHED");
                    currentQuestion = 0;
                }
                else showQuestion();
            });

        }
    }

}
