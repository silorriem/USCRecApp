package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityGmapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGmapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("Hello world");

        binding = ActivityGmapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<RecCenter> recCenters = new ArrayList<>();
        System.out.println("Map is ready");



        //put marker to map
        LatLng lyon = new LatLng(34.024555845264075, -118.28840694512736);
        mMap.addMarker(new MarkerOptions().position(lyon).title("lyon")).setTag(RecCenter.lyon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lyon,15));
        LatLng cromwell = new LatLng(34.0222295647154, -118.28784044512746);
        mMap.addMarker(new MarkerOptions().position(cromwell).title("Cromwell Track")).setTag(RecCenter.Cromwell_Track);
        LatLng uac = new LatLng(34.024203589076414, -118.2879799201736);
        mMap.addMarker(new MarkerOptions().position(uac).title("UAC Lap Swim")).setTag(RecCenter.uac);


        //set marker onclick event
        GoogleMap.OnMarkerClickListener listener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Intent intent = new Intent(GMapsActivity.this, BookingPageActivity.class);
                intent.putExtra("RecCenter",
                        marker.getTitle().equals("lyon") ?
                        RecCenter.lyon :
                        marker.getTitle().equals("Cromwell Track") ?
                        RecCenter.Cromwell_Track :
                        RecCenter.uac);
                startActivity(intent);

                return false;
            }
        };
        mMap.setOnMarkerClickListener(listener);
    }

    public List<Appointment> readUser(String uscid){
        List<Appointment> appointments = new ArrayList<>();
        Database.db
                .collection("User")
                .whereEqualTo("USCID", uscid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                User user = doc.toObject(User.class);
                                appointments.addAll(user.getUpcoming());
                            }
                        }
                    }
                });
        return appointments;
    }

    public void toSummary(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

