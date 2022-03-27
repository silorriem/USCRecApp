package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        // fetch and store all the data fields inside multiple arrays
        ArrayList<TimeSlot> timeSlots = currentLocation.getTimeSlots();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> capacities = new ArrayList<>();
        ArrayList<String> availabilities = new ArrayList<>();

        for(TimeSlot i: timeSlots) {
            Date date = i.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
        }

        // find the view, create an array adapter to display all the time slots
        ListView listView = (ListView) findViewById(R.id.bookingPage);
        ///ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.booking_page, timeSlots);
    }

    public void displayView(){}
    public void displaySelection(){}
    public RecCenter getCurrentLocation(){return currentLocation;}
    public void setCurrentLocation(RecCenter rc){currentLocation = rc;}
    public void onClickSelection(View view){}
}
