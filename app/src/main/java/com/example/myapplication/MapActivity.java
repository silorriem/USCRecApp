package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {
    private static ArrayList<RecCenter> recCentersList;
    private static User user;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //get user info
        //TODO
    }
    public void displayView(){}
    public void displayRec(){}
    public void displayRecCenter(){}
    public void onClickRecCenter(View view){
        Intent intent = new Intent(this, BookingPageActivity.class);
        startActivity(intent);
    }
    public void onClickWindow(View view, RecCenter recCenter){
        Intent intent = new Intent(this, SummaryPageDisplay.class);
        intent.putExtra("RecCenter", recCenter.name);
        startActivity(intent);
    }
}
