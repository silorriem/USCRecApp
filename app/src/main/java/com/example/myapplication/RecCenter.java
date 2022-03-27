package com.example.myapplication;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

public class RecCenter implements Serializable {
    String name;
    double longitude;
    double latitude;
    ArrayList<TimeSlot> timeSlots;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    // other methods
    void makeAppointment(TimeSlot slot) {
        timeSlots.add(slot);
        // TODO
    }

    void deleteAppointment(TimeSlot slot, User user, boolean availability) {
        // TODO
    }
}
