package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {
    private static ArrayList<RecCenter> recCentersList;
    private static User user;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        //get user info
        //TODO

        for(Appointment appointment: user.getUpcoming()){
            Button btn = new Button(this);
            btn.setText(appointment.toString());
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickWindow(view, appointment.getLocation());
                }
            });

        }


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
