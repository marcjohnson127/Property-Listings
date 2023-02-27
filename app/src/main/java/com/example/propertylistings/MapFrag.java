package com.example.propertylistings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFrag extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FrameLayout map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_frag);

        map = findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        this.gMap = googleMap;

        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.getUiSettings().setCompassEnabled(true);
        gMap.getUiSettings().setZoomGesturesEnabled(true);
        gMap.getUiSettings().setRotateGesturesEnabled(true);
        gMap.getUiSettings().setScrollGesturesEnabled(true);


        String location = PropertyInfo.chosenAddress;
        if (location == null) {
            Toast.makeText(getApplicationContext(), ShowListings.chosenItem, Toast.LENGTH_SHORT).show();
        } else {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> listAddress = geocoder.getFromLocationName(location, 1);
                if (listAddress.size() > 0) {
                    LatLng latLng = new LatLng(listAddress.get(0).getLatitude(), listAddress.get(0).getLongitude());

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.title(PropertyInfo.chosenAddress);
                    markerOptions.position(latLng);
                    this.gMap.addMarker(markerOptions);
                    //this.gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    this.gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /*
        LatLng mapIndia = new LatLng(20.5937, 78.9629);
        this.gMap.addMarker(new MarkerOptions().position(mapIndia).title("Marker in India"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapIndia));


         */


    }
}