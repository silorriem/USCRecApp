package com.example.myapplication;

import java.util.ArrayList;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryPageActivity extends AppCompatActivity {
    private ArrayList<Appointment> myAppointments;
    public SummaryPageActivity(ArrayList<Appointment> appoints){myAppointments = appoints;}

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
