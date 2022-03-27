package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TimeSlotActivity extends AppCompatActivity {
    TimeSlot currTimeSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // map to the booking page layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_slot_detail);

        // set the visibility of those two buttons
        Button remindMe = (Button) findViewById(R.id.remindMe);
        Button signUp = (Button) findViewById(R.id.signUp);
        if(currTimeSlot.isAvailable()) {
            remindMe.setEnabled(false);
            signUp.setEnabled(true);
        } else {
            remindMe.setEnabled(true);
            signUp.setEnabled(false);
        }

        // set on click activity
        remindMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }
}
