package com.example.myapplication;

import java.util.Date;
import java.util.List;

public class TimeSlot {
    Integer startingHour;
    Integer startingMinute;
    Integer endingHour;
    Integer endingMinute;
    Date date;
    boolean available;
    Integer capacity;
    List<User> waitingList;

    // getters and setters
    public Integer getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(Integer startingHour) {
        this.startingHour = startingHour;
    }

    public Integer getStartingMinute() {
        return startingMinute;
    }

    public void setStartingMinute(Integer startingMinute) {
        this.startingMinute = startingMinute;
    }

    public Integer getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(Integer endingHour) {
        this.endingHour = endingHour;
    }

    public Integer getEndingMinute() {
        return endingMinute;
    }

    public void setEndingMinute(Integer endingMinute) {
        this.endingMinute = endingMinute;
    }

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
        return "TimeSlot{" +
                "startingHour=" + startingHour +
                ", startingMinute=" + startingMinute +
                ", endingHour=" + endingHour +
                ", endingMinute=" + endingMinute +
                ", date=" + date +
                ", available=" + available +
                ", capacity=" + capacity +
                ", waitingList=" + waitingList +
                '}';
    }
}
