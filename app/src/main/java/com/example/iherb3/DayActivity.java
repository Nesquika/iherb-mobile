package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

public class DayActivity extends AppCompatActivity {
    ImageView menu;
    int[] progress = new int[30];
    CheckBox chb;
    ProgressBar progressBar;
    Intent month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        month = new Intent(this, MonthActivity.class);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(viewClickListener);
        chb = findViewById(R.id.checkbox);
        progressBar = findViewById(R.id.progress);
        chb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    progressBar.setProgress(100);
                    month.putExtra("progress", 100);
                } else {
                    progressBar.setProgress(50);
                    month.putExtra("progress", 50);
                }
            }
        });
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
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
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
            public void onDismiss(PopupMenu menu) {
            }
        });
        popupMenu.show();
    }

    public void openBads(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openMonth(View view){
        startActivity(month);
    }

}