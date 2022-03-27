package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
            dates.add(strDate);

            DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            Date endDate = addHoursToJavaUtilDate(date, 1);
            String strTime = timeFormat.format(date) + timeFormat.format(endDate);
            times.add(strTime);

            String cap = Integer.toString(i.getCapacity());
            capacities.add(cap);

            String availability = String.valueOf(i.getWaitingList());
            availabilities.add(availability);
        }

        // construct a list of hashmap for the content of the listView
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
        for(int i = 0; i < timeSlots.size(); ++i) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("date", dates.get(i));
            map.put("time", times.get(i));
            map.put("capacity", capacities.get(i));
            map.put("available", availabilities.get(i));
            listItem.add(map);
        }

        // construct a simple adapter
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                listItem,
                R.layout.booking_page_row_items,
                new String[]{"date", "time", "capacity", "availability"},
                new int[]{R.id.date, R.id.time, R.id.capacity, R.id.availability});

        // find the view, create an array adapter to display all the time slots
        ListView listView = (ListView) findViewById(R.id.bookingPage);

        // bing the adapter to the list view
        listView.setAdapter(mSimpleAdapter);
    }

    public void displayView(){}
    public void displaySelection(){}
    public RecCenter getCurrentLocation(){return currentLocation;}
    public void setCurrentLocation(RecCenter rc){currentLocation = rc;}
    public void onClickSelection(View view){}

    // helper function for date calculation
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
