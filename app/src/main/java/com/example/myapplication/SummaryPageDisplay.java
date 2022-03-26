package com.example.myapplication;

import java.util.ArrayList;
import android.view.View;

public class SummaryPageDisplay extends Display {
    private ArrayList<Appointment> myAppointments;
    public SummaryPageDisplay(ArrayList<Appointment> appoints){myAppointments = appoints;}
    @Override
    public void displayView(){}
    public void onClickReturn(){}
    public void onClickDelete(View view){}

    public ArrayList<Appointment> getMyAppointments() {
        return myAppointments;
    }

    public void setMyAppointments(ArrayList<Appointment> myAppointments) {
        this.myAppointments = myAppointments;
    }
}
