package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DeleteAppointmentActivity extends AppCompatActivity {
    String recCenterName, currDate, currTime, currPrevOrCurr, USCIDNumber;
    int delNum;
    Serializable appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);

        if(Database.db == null) {
            Database.db = FirebaseFirestore.getInstance();
        }

        // enable tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null) {
            delNum = (int) intent.getSerializableExtra("DeleteNum");
            recCenterName = (String) intent.getSerializableExtra("RecCenter");
            currDate = (String) intent.getSerializableExtra("Date");
            currTime = (String) intent.getSerializableExtra("Time");
            currPrevOrCurr = (String) intent.getSerializableExtra("prevOrCurrent");
            appointment = (Serializable) intent.getSerializableExtra("Appointment");

        }
        LinearLayout llDel = findViewById(R.id.linearLayoutDel);
        TextView textView = new TextView(this);
        textView.setText("Rec Center Name: " + recCenterName + "\n" + currDate + "\n" + currTime);
        textView.setTextSize(16);
        textView.setTextAlignment(textView.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        textView.setLayoutParams(params);
        llDel.addView(textView);

        findViewById(R.id.delButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                USCIDNumber = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                DocumentReference userRef = Database.db.collection("User").document(USCIDNumber);
                userRef.update("Appointments", FieldValue.arrayRemove(appointment)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Deletion message: ","delete was successful");
                            Toast.makeText(getApplicationContext(),"successfully deleted appointment",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("Deletion message: ",task.getException().getMessage() + "\n\n\n",task.getException());
                        }
                    }
                });
                DeleteAppointmentActivity.this.finish();
                Intent intent = new Intent(DeleteAppointmentActivity.this,SummaryPageActivity.class);
                startActivity(intent);
            }
        });
    }

    // enable the back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(DeleteAppointmentActivity.this,SummaryPageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}