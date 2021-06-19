package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.LocalDate;

public class RegistrationActivity extends AppCompatActivity {
    EditText email, password;
    Button login, register;
    RadioGroup gender;
    boolean isEmail, isPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) isEmail = true;
                else isEmail = false;
                changeRegisterButton();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) isPassword = true;
                else isPassword = false;
                changeRegisterButton();
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeRegisterButton();
            }
        });
    }


    public void createProfile(View view){
        Toast.makeText(getApplicationContext(), "Регистрация завершена успешно!", Toast.LENGTH_SHORT).show();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    public void checkUser(View view){
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        /*if (findUser(sEmail, sPassword)) {
            Intent bads = new Intent(this, MainActivity.class);
            bads.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(bads);
            Toast.makeText(getApplicationContext(), "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void changeRegisterButton(){
        if (isEmail && isPassword && gender.getCheckedRadioButtonId() != -1) register.setEnabled(true);
        else register.setEnabled(false);
    }
}