package com.example.myapplication;

import android.view.View;

public class BookingPageDisplay extends Display {
    private RecCenter currentLocation;
    public BookingPageDisplay(RecCenter recCenter){currentLocation = recCenter;}
    @Override
    public void displayView(){}
    public void displaySelection(){}
    public RecCenter getCurrentLocation(){return currentLocation;}
    public void setCurrentLocation(RecCenter rc){currentLocation = rc;}
    public void onClickSelection(View view){}
}
