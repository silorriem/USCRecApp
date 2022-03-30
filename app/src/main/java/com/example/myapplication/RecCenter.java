package com.example.myapplication;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

public class RecCenter implements Serializable {
    String name;
    double longitude;
    double latitude;
    ArrayList<TimeSlot> timeSlots;

    private static ArrayList<TimeSlot> lyon_timeslot = new ArrayList<>(Arrays.asList(new TimeSlot("lyon", new Date(2022, 4, 1, 9, 0),10 )));
    static RecCenter lyon = new RecCenter("lyon", 34.024555845264075, -118.28840694512736, lyon_timeslot);

    public RecCenter(){
        name = "";
        latitude = 0;
        longitude = 0;
        timeSlots = new ArrayList<>();
    }

    public RecCenter(String name, double longitude, double latitude, ArrayList<TimeSlot> timeSlots){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeSlots = timeSlots;
    }


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
