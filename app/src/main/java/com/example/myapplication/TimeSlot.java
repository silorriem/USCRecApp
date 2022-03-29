package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TimeSlot implements Serializable {
    final Integer duration = 1;
    Date date;
    Integer capacity;
    Integer currentRegistered;
    String recCenter;
    String slotId;
    List<String> waitingList;

    // getters and setters
    public Integer getCurrentRegistered() {
        return currentRegistered;
    }

    public void setCurrentRegistered(Integer currentRegistered) {
        this.currentRegistered = currentRegistered;
    }

    public TimeSlot() {
        date = new Date();
        capacity = 1;
        waitingList = new LinkedList<>();
        currentRegistered = 0;
    }

    public TimeSlot(Date d, int c) {
        date = d;
        capacity = c;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return currentRegistered < capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void register(User user){
        if (isAvailable()){
            currentRegistered++;
            addToWaitingList(user);
        }
    }

    public String getRecCenter() {
        return recCenter;
    }

    public void setRecCenter(String recCenter) {
        this.recCenter = recCenter;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public List<String> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<String> waitingList) {
        this.waitingList = waitingList;
    }

    // other methods
    void addToWaitingList(User user) {
        waitingList.add(user.getUSCID());
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
        int remain = capacity - currentRegistered;
        return date.toString() + " current available spots: "+ (Math.max(remain, 0));
    }
}
