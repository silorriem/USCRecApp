package com.example.myapplication;

public class Appointment {
    RecCenter location;
    TimeSlot timeInterval;
    boolean successfullyBooked;
    User user;

    // getters and setters
    public RecCenter getLocation() {
        return location;
    }

    public void setLocation(RecCenter location) {
        this.location = location;
    }

    public TimeSlot getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeSlot timeInterval) {
        this.timeInterval = timeInterval;
    }

    public boolean isSuccessfullyBooked() {
        return successfullyBooked;
    }

    public void setSuccessfullyBooked(boolean successfullyBooked) {
        this.successfullyBooked = successfullyBooked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // other methods

    void remind(Appointment appointment) {

    }
}
