package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TimeSlot implements Serializable {
    final Integer duration = 1;
    Date date;
    Integer capacity;
    List<User> currentRegistered;
    List<User> waitingList;

    public TimeSlot(){
        date = new Date();
        capacity = 1;
        waitingList = new LinkedList<>();
        currentRegistered = new LinkedList<>();
    }

    public TimeSlot(Date date, int capacity){

    }

    // getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return currentRegistered.size() < capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void register(User user){
        if (isAvailable()){
            currentRegistered.add(user);
        }else {
            addToWaitingList(user);
        }
    }

    public List<User> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<User> waitingList) {
        this.waitingList = waitingList;
    }

    // other methods
    void addToWaitingList(User user) {
        waitingList.add(user);
    }

    void notifyUsersInWaitingList() {
        // TODO
    }

    //pop the first user from waitlist
    void removeFromWaitingList() {
        // TODO
        waitingList.remove(0);
    }

    @Override
    public String toString() {
        // TODO
        int remain = capacity - currentRegistered.size();
        return date.toString() + " current available spots: "+ (Math.max(remain, 0));
    }
}
