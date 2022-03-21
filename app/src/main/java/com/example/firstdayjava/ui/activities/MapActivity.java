package com.example.firstdayjava.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ActivityMapBinding;
import com.example.firstdayjava.ui.fragments.EditFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.InfoWindowAdapter {
    ActivityMapBinding bd;
    GoogleMap map;

    final int START_CODE = 101;

    String[] styles = {"Standard", "Waite", "Red"};
    ArrayAdapter adapter;

    FusedLocationProviderClient locationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.backColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();
    }

    private void init() {
        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, styles);
        bd.spinner.setAdapter(adapter);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);


        locationProviderClient = new FusedLocationProviderClient(this);

        initEvent();
    }

    private boolean checkReqPermission() {
        int ACCESS_COARSE_LOCATION = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
        );

        int ACCESS_FINE_LOCATION = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
        );

        int ACCESS_BACKGROUND_LOCATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ACCESS_BACKGROUND_LOCATION = ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_BACKGROUND_LOCATION
            );
        } else {
            ACCESS_BACKGROUND_LOCATION = PackageManager.PERMISSION_GRANTED;
        }

        boolean hasPermission = ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED &&
                ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED;

        if (!hasPermission) {

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, START_CODE);
            } else {
                Toast.makeText(this, "Location Service not supported", Toast.LENGTH_SHORT).show();
            }
        }

        return hasPermission;
    }

    private void initEvent() {
        findViewById(R.id.myLocBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chackAndGetUserLocation();
            }
        });

        bd.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (bd.spinner.getSelectedItemPosition()) {
                    case 0:
                        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                MapActivity.this, R.raw.style_json3
                        ));
                        break;
                    case 1:
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                MapActivity.this, R.raw.style_json2
                        ));
                        break;
                    case 2:
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                MapActivity.this, R.raw.style_json
                        ));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bd.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String locationStr = marker.getPosition().latitude + "," + marker.getPosition().longitude;
                intent.putExtra(EditFragment.LOCATION_STR, locationStr);
                intent.putExtra(EditFragment.LOCATION_STR_GEOTITLE, bd.locTitle.getText().toString());
                MapActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    Marker marker = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json3
        ));

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                addMarker(latLng);
            }
        });

        map.setLatLngBoundsForCameraTarget(
                new LatLngBounds(
                        new LatLng(21.6, 24.7), // NE bounds
                        new LatLng(30, 34)  // NE bounds
                )
        );


//        map.setInfoWindowAdapter(this);

        checkReqPermission();
    }

    private void addMarker(LatLng latLng) {
        if (marker != null)
            marker.remove();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.man);

        marker = map.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .title("User Location")
                .icon(icon)
                .anchor(0.5f, 0.5f)
                .flat(true));

        assert marker != null;
        marker.setTag(0);
//        marker.setRotation(180);
        bd.saveBtn.setVisibility(View.VISIBLE);

        setLocationTitle(marker.getPosition());
    }

    private void setLocationTitle(LatLng position) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);

            if (addresses.size() > 0) {
                String str = addresses.get(0).getCountryName();
                str += "," + addresses.get(0).getLocality();
                str += "," + addresses.get(0).getFeatureName();
                bd.locTitle.setText(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    private void chackAndGetUserLocation() {
        if (checkReqPermission()) {
            getUserLocation();
        } else {
            Toast.makeText(this, "Permission not allowed", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        locationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        gotToLocation(location);
                    }

                    private void gotToLocation(Location location) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                                latLng,
                                15
                        );

                        map.moveCamera(cameraUpdate);

                        map.getUiSettings().setAllGesturesEnabled(true);
                        map.getUiSettings().setZoomControlsEnabled(false);
                        map.getUiSettings().setCompassEnabled(false);
                        map.getUiSettings().setMapToolbarEnabled(false);

                        map.setBuildingsEnabled(true);
                        addMarker(latLng);
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == START_CODE) {
            getUserLocation();
        }
    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        int clickTime = ((int) marker.getTag());
        marker.setTitle("Click Save");
        marker.setSnippet("if this is the location " + clickTime);
        marker.setTag(clickTime + 1);
        return false;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return LayoutInflater.from(this).inflate(R.layout.dialog_userinfo, null);
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return LayoutInflater.from(this).inflate(R.layout.dialog_userinfo, null);
    }
}