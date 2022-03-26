package com.example.myapplication;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {
    private ArrayList<RecCenter> recCentersList;
    private SummaryPageDisplay sumPage;
    public MapActivity(ArrayList<RecCenter> recs, SummaryPageDisplay sum){
        recCentersList = recs;
        sumPage = sum;
    }
    public void displayView(){}
    public void displayRec(){}
    public void displayRecCenter(){}
    public void onClickRecCenter(View view){}
    public void onClickWindow(View view){}
}
