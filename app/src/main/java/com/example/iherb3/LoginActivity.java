package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    boolean isEmail, isPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) isEmail = true;
                else isEmail = false;
                changeLoginButton();
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
                changeLoginButton();
            }
        });
    }

    private void changeLoginButton(){
        if (isEmail && isPassword) login.setEnabled(true);
        else login.setEnabled(false);
    }

    public void checkUser(View view){
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        if (findUser(sEmail, sPassword)) {
            Intent bads = new Intent(this, MainActivity.class);
            bads.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(bads);
        }
    }

    private boolean findUser(String email, String password){
        return true;
    }
}