package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookingPageActivity extends AppCompatActivity {
    public static RecCenter currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // map to the booking page layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        // get the rec center object from the intent
        Intent intent = getIntent();
        currentLocation = (RecCenter)intent.getSerializableExtra("recreation center");

        // create an arrayList of the rec center's time slots
        ArrayList<TimeSlot> timeSlots = currentLocation.getTimeSlots();

        // find the view, create an array adapter to display all the time slots
        ListView listView = (ListView) findViewById(R.id.bookingPage);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.booking_page, timeSlots);
    }

    public void displayView(){}
    public void displaySelection(){}
    public RecCenter getCurrentLocation(){return currentLocation;}
    public void setCurrentLocation(RecCenter rc){currentLocation = rc;}
    public void onClickSelection(View view){}
}
