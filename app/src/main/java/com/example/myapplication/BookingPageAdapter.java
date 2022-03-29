//package com.example.myapplication;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.sql.Time;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//
//public class BookingPageAdapter extends ArrayAdapter<TimeSlot> {
//    // constructor
//    public BookingPageAdapter(@NonNull Context context, ArrayList<TimeSlot> timeSlots) {
//        super (context, 0, timeSlots);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listItemView = convertView;
//
//        // we are getting data from array list inside
//        // our modal class.
//        TimeSlot timeSlot = getItem(position);
//
//        // initializing our UI components of list view item.
//        TextView date = listItemView.findViewById(R.id.date);
//        TextView time = listItemView.findViewById(R.id.time);
//        TextView availability = listItemView.findViewById(R.id.availability);
//
//        // after initializing our items we are
//        // setting data to our view.
//        // below line is use to set data to our text view.
//        Date dateValue = timeSlot.getDate();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String strDate = "Date: " + dateFormat.format(dateValue);
//        date.setText(strDate);
//
//        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
//        Date endDate = addHoursToJavaUtilDate(timeSlot.getDate(), 1);
//        String strTime = "Time: " + timeFormat.format(date) + " - " +timeFormat.format(endDate);
//        time.setText(strTime);
//
//        String remainingSpots = "Remaining: " + Integer.toString(timeSlot.getCapacity() - timeSlot.currentRegistered);
//        availability.setText(remainingSpots);
//
//        // below line is use to add item click listener
//        // for our item of list view.
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TimeSlotActivity.class);
//                intent.putExtra("TimeSlot", timeSlot.getSlotId());
//                intent.putExtra("RecCenter", timeSlot.getRecCenter());
//                getContext().startActivity(intent);
//            }
//        });
//        return listItemView;
//    }
//
//    // helper function for date calculation
//    public Date addHoursToJavaUtilDate(Date date, int hours) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.HOUR_OF_DAY, hours);
//        return calendar.getTime();
//    }
//}
