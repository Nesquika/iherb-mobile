package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView result;
    Button btn;
    ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.resultMessage);
        result.setText(getResources().getText(R.string.resultMessage1 ) + " витамин");
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(viewClickListener);
        btn = findViewById(R.id.btnOpenProgs);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrograms(v);
            }
        };
        btn.setOnClickListener(listener);
    }


    public void openPrograms(View view){
        Intent intent = new Intent(this, ProgramsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    public void openBads(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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
                        intent = new Intent(getBaseContext(), ProgramsActivity.class);
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
}