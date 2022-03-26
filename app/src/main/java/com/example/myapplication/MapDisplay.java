package com.example.myapplication;

import android.view.View;
import java.util.ArrayList;


public class MapDisplay extends Display {
    private ArrayList<RecCenter> recCentersList;
    private SummaryPageDisplay sumPage;
    public MapDisplay(ArrayList<RecCenter> recs, SummaryPageDisplay sum){
        recCentersList = recs;
        sumPage = sum;
    }
    @Override
    public void displayView(){}
    public void displayRec(){}
    public void displayRecCenter(){}
    public void onClickRecCenter(View view){}
    public void onClickWindow(View view){}
}
