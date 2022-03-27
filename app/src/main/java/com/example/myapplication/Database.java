package com.example.myapplication;

import com.google.firebase.firestore.FirebaseFirestore;

public class Database {

    private FirebaseFirestore db;
    public Database(){
        db = FirebaseFirestore.getInstance();
    }
    public void addUser(User user){}
    public void changeProfile(){}
}
