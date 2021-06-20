package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    ImageView menu;
    RadioGroup radioGroup;
    TextView question, status;
    String[] questions = {"Принимаете ли вы какие-либо БАДы?",
            "Какой образ жизни Вы ведете?",
            "Есть ли у Вас аллергии?",
            "Какую основную цель вы преследуете?",
            "Замечали ли Вы, что начали чаще уставать, снизилась работоспособность?"};
    String[][] answers = {
            {"Да, я уже принимаю некоторые препараты", "Нет, но планирую начать"},
            {"Малоподвижный", "Нерегулярно тренируюсь", "Занятия спортом не реже двух раз в неделю", "Интенсивно занимаюсь спортом"},
            {"Да, я аллергик", "Нет"},
            {"Набрать массу","Улучшить структуры волос/ногтей", "Укрепить кости", "Профилактика простуды и гриппа"},
            {"Да","Нет"}};
    int[] actualAnswers;
    Button finish;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(viewClickListener);

        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.answers);
        finish = findViewById(R.id.finish);
        status = findViewById(R.id.status);
        count = 0;
        setFields(count);

        actualAnswers = new int[questions.length];
    }
    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {Intent intent;
                switch (item.getItemId()) {
                    case R.id.menu1:
                        intent = new Intent(getBaseContext(), TestActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        return true;
                    case R.id.menu2:
                        intent = new Intent(getBaseContext(), DayActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        return true;
                    case R.id.menu3:
                        intent = new Intent(getBaseContext(), MyProgramActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });


        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {}
        });
        popupMenu.show();
    }
    private void setFields(int num){
        question.setText(questions[num]);
        radioGroup.removeAllViews();
        for (int i = 0; i < radioGroup.getChildCount(); i++){
            radioGroup.removeViewAt(i);
        }
        status.setText("Вопрос "+ (num+1) + " из " + questions.length);
        for (int i = 0; i < answers[num].length; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(answers[num][i]);
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,30,0,0);
            rb.setLayoutParams(lp);
            rb.setBackground(AppCompatResources.getDrawable(this, R.drawable.radio));
            rb.setButtonTintList(new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled},
                            new int[]{android.R.attr.state_enabled}},
                    new int[] {
                            Color.WHITE,Color.WHITE})
            );
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton rb, boolean isChecked){
                    if (isChecked) {
                        rb.setBackground(AppCompatResources.getDrawable(getBaseContext(), R.drawable.radio_active));
                        rb.setTextColor(Color.WHITE);
                    }
                    else {
                        rb.setBackground(AppCompatResources.getDrawable(getBaseContext(), R.drawable.radio));
                        rb.setTextColor(Color.BLACK);
                    }
                }
            });
            radioGroup.addView(rb);
        }
    }
    public void openBads(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openPreviousQuestion(View view){
        if (count < 1) return;
        count--;
        setFields(count);
        checkButton();
    }

    public void openNextQuestion(View view){
        if (count > questions.length-2) return;
        saveAnswer();
        count++;
        setFields(count);
        checkButton();
    }

    private void checkButton() {
        if (count == questions.length-1) finish.setEnabled(true);
        else finish.setEnabled(false);
    }

    public void finishTest(View view){
        saveAnswer();
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    private void saveAnswer(){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);
        actualAnswers[count] = idx;
    }
}
