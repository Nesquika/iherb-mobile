package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public int user = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent;
        //if (user == -1)
            intent = new Intent(this, LoginActivity.class);
        //else intent = new Intent (this, BadsActivity.class);
        startActivity(intent);
    }
}