package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class BookingPageActivity extends AppCompatActivity {
    public static RecCenter currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // map to the booking page layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        // get the rec center object from the intent
//        Intent intent = getIntent();
//        currentLocation = (RecCenter)intent.getSerializableExtra("RecCenter");

        // a test rec center
        RecCenter test = new RecCenter();
        test.name = "Lyon Center";
        test.latitude = 34.12345;
        test.longitude = 108.45678;
        test.timeSlots = new ArrayList<>();

        TimeSlot temp = new TimeSlot();
        temp.date = new GregorianCalendar(2022, Calendar.MARCH, 28).getTime();
        temp.capacity = 5;
        temp.currentRegistered = 0;
        temp.waitingList = new ArrayList<>();

        test.timeSlots.add(temp);

        currentLocation = test;


        // fetch and store all the data fields inside multiple arrays
        ArrayList<TimeSlot> timeSlots = currentLocation.getTimeSlots();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
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

            String remainingSpots = Integer.toString(i.getCapacity() - i.currentRegistered);
            availabilities.add("Remaining: " + remainingSpots);
        }

        // construct a list of hashmap for the content of the listView
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
        for(int i = 0; i < timeSlots.size(); ++i) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("date", dates.get(i));
            map.put("time", times.get(i));
            map.put("available", availabilities.get(i));
            listItem.add(map);
        }

        // construct a simple adapter
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                listItem,
                R.layout.booking_page_row_items,
                new String[]{"date", "time", "availability"},
                new int[]{R.id.date, R.id.time, R.id.availability});

        // find the view, create an array adapter to display all the time slots
        ListView listView = (ListView) findViewById(R.id.bookingPage);

        // bind the adapter to the list view
        listView.setAdapter(mSimpleAdapter);

        // set up on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BookingPageActivity.this, TimeSlotActivity.class);
                intent.putExtra("TimeSlot", timeSlots.get(i));
                startActivity(intent);
            }
        });
    }

    // helper function for date calculation
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
