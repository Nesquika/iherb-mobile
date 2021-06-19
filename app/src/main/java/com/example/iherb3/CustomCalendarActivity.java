package com.example.iherb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CustomCalendarActivity extends AppCompatActivity implements OnNavigationButtonClickedListener {

    CustomCalendar customCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_calendar);
        customCalendar = findViewById(R.id.custom_calendar);

        Map<Object, Property> descHashMap = new HashMap<>();

        Property defaultProperty = new Property();
        defaultProperty.layoutResource = R.layout.default_view;
        defaultProperty.dateTextViewResource = R.id.default_date;
        descHashMap.put("default", defaultProperty);

        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.current_date;
        descHashMap.put("current", currentProperty);

        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.present_date;
        descHashMap.put("present", presentProperty);

        customCalendar.setMapDescToProp(descHashMap);

        Map<Integer, Object> dateHashMap = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");
        dateHashMap.put(1, "present");
        dateHashMap.put(2, "present");
        dateHashMap.put(3, "present");

        customCalendar.setDate(calendar, dateHashMap);
        customCalendar.setOnDateSelectedListener((view, selectedDate, desc) -> {
            String sDate = selectedDate.get(Calendar.DAY_OF_MONTH) +
                    "/" + (selectedDate.get(Calendar.MONTH)) +
                    "/" + (selectedDate.get(Calendar.YEAR));

            Toast.makeText(getApplicationContext(), sDate, Toast.LENGTH_SHORT).show();

        });
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this);
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);
    }

    @Override
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {
        Map<Integer, Object>[] arr = new Map[2];
        switch(newMonth.get(Calendar.MONTH)) {
            case Calendar.JUNE:
                arr[0] = new HashMap<>(); //This is the map linking a date to its description
                arr[0].put(1, "present");
                arr[0].put(2, "present");
                arr[0].put(3, "present");
                arr[0].put(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), "current");
                break;
            case Calendar.AUGUST:
//                arr[0] = new HashMap<>();
//                arr[0].put(5, "present");
//                arr[0].put(10, "present");
//                arr[0].put(19, "present");
                break;
        }
        return arr;
    }
}