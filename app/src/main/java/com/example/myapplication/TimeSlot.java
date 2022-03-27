package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TimeSlot implements Serializable {
    final Integer duration = 1;
    Date date;
    boolean available;
    Integer capacity;
    List<User> waitingList;

    // getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<User> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<User> waitingList) {
        this.waitingList = waitingList;
    }

    // other methods
    void addToWaitingList(User user) {
        // TODO
    }

    void notifyUsersInWaitingList() {
        // TODO
    }

    void removeFromWaitingList() {
        // TODO
    }

    @Override
    public String toString() {
        // TODO
        return null;
    }
}
