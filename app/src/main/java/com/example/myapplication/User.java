package com.example.myapplication;
import java.util.*;

public class User {
    String userName;
    String USCID;
    String email;
    String photoFileName;
    List<Appointment> Appointments;

    // the constructor
    public User(String userName, String USCID, String email, String photoFileName, List<Appointment> appointments) {
        this.userName = userName;
        this.USCID = USCID;
        this.email = email;
        this.photoFileName = photoFileName;
        Appointments = appointments;
    }

    // getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUSCID() {
        return USCID;
    }

    public void setUSCID(String USCID) {
        this.USCID = USCID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public List<Appointment> getAppointments() {
        return Appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        Appointments = appointments;
    }

    // other methods

    // insert a new appointment of the user
    void insertAppointment(Appointment appointment) {
        Appointments.add(appointment);
    }

    // upon appointment removal, notify all other users who are in the waiting list
    void notifyWaitList() {
        // TODO
    }
}
