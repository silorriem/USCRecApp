package com.example.myapplication;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SummaryPageActivity extends AppCompatActivity {
    private ArrayList<Appointment> myAppointments;
    String USCIDNumber;
    public static final String TAG = "Firebase Message: ";


    public void displayView(){}
    public void onClickReturn(){}
    public void onClickDelete(View view){}

    public ArrayList<Appointment> getMyAppointments() {
        return myAppointments;
    }

    public void setMyAppointments(ArrayList<Appointment> myAppointments) {
        this.myAppointments = myAppointments;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page);

        // initialize the database if it's uninitialized yet
        if(Database.db == null) {
            Database.db = FirebaseFirestore.getInstance();
        }

        // enable tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get current User info - appointments
        USCIDNumber = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        DocumentReference userRef = Database.db.collection("User").document(USCIDNumber);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        //Log.d(TAG,"DocumentSnapshot data: " + doc.getData());
                        ArrayList<Object> apptArray = (ArrayList<Object>) doc.get("Appointments");
                        if (apptArray == null)
                            return;

                        // fetch and store all the data fields inside multiple arrays
                        ArrayList<String> recCenterNames = new ArrayList<>(), dates = new ArrayList<>(),
                                times = new ArrayList<>(), prevOrCurrents = new ArrayList<>();
                        ArrayList<Integer> indices = new ArrayList<>();
                        int count = 0;
                        for (Object o : apptArray) {
                            Map appt = (Map) o;
                            Boolean successfullyBooked = (boolean)appt.get("successfullyBooked");

                            if (successfullyBooked == null) {
                                Log.d(TAG,"missing successfullyBooked: skipping appt");
                                count++;
                                continue;
                            }

                            if (!successfullyBooked) {
                                count++;
                                continue;
                            }
                            String recCenterName = (String) appt.get("recCenterName");

                            if (recCenterName == null) {
                                Log.d(TAG,"missing recCenterName: skipping appt");
                                count++;
                                continue;
                            }
                            Map timeInt = (Map) appt.get("timeInterval");
                            if (timeInt == null) {
                                Log.d(TAG,"missing timeInt: skipping appt");
                                count++;
                                continue;
                            }
                            Timestamp timeStamp = (Timestamp) timeInt.get("date");

                            if (timeStamp == null) {
                                Log.d(TAG,"missing timeStamp: skipping appt");
                                count++;
                                continue;
                            }
                            indices.add(count);
                            recCenterNames.add(recCenterName);
                            Date apptDate = timeStamp.toDate();

                            Date currentDate = new Date();
                            String prevOrCurr = (apptDate.compareTo(currentDate) > 0) ? "current" : "previous";
                            boolean isCurr = prevOrCurr.equals("current");
                            prevOrCurr += " appointment";
                            prevOrCurrents.add(prevOrCurr);

                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate = "Date: " + dateFormat.format(apptDate);

                            dates.add(strDate);

                            DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                            Date endDate = addHoursToJavaUtilDate(apptDate, (Long) timeInt.get("duration"));

                            String strTime = "Time: " + timeFormat.format(apptDate) + " - " +timeFormat.format(endDate);
                            times.add(strTime);
                            Log.d(TAG,"RecCenter Name: " + recCenterName + "\ndate: " + strDate + "\ntime: " + strTime + "\nprevOrCurr: " + prevOrCurr);
                        count++;
                        }

                        // construct a list of hashmap for the content of the listView
                        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
                        for(int i = 0; i < recCenterNames.size(); ++i) {
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("recCenterName", recCenterNames.get(i));
                            map.put("date", dates.get(i));
                            map.put("time", times.get(i));
                            map.put("prevOrCurrent", prevOrCurrents.get(i));
                            listItem.add(map);
                        }

                        // construct a simple adapter
                        SimpleAdapter mSimpleAdapter = new SimpleAdapter(SummaryPageActivity.this,
                                listItem,
                                R.layout.summary_page_row_items,
                                new String[]{"recCenterName", "date", "time","prevOrCurrent"},
                                new int[]{R.id.RecCenterName, R.id.dateSum, R.id.timeSum,R.id.prevOrCurrent});

                        // find the view, create an array adapter to display all the time slots
                        ListView listView = (ListView) findViewById(R.id.summaryPage);

                        // bind the adapter to the list view
                        listView.setAdapter(mSimpleAdapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                if (prevOrCurrents.get(i).equals("current appointment")) {
                                    Intent intent = new Intent(SummaryPageActivity.this, DeleteAppointmentActivity.class);
                                    intent.putExtra("DeleteNum", indices.get(i));
                                    intent.putExtra("RecCenter", recCenterNames.get(i));
                                    intent.putExtra("Date",dates.get(i));
                                    intent.putExtra("Time",times.get(i));
                                    intent.putExtra("Appointment", (Serializable) apptArray.get(indices.get(i)));

                                    SummaryPageActivity.this.finish();
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    else {
                        Log.d(TAG,"No such doc");
                    }
                }
                else {
                    Log.d(TAG,"get failed with", task.getException());
                }
            }
        });


    }

    // enable the back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // helper function for date calculation
    public static Date addHoursToJavaUtilDate(Date date, Long hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            calendar.add(Calendar.HOUR_OF_DAY, Math.toIntExact(hours));
        }
        catch(ArithmeticException e) {
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        return calendar.getTime();
    }
}
