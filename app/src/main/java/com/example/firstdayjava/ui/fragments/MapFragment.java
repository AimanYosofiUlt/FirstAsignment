package com.example.firstdayjava.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentMapBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {
    FragmentMapBinding bd;


    GoogleMap map;

    final int START_CODE = 101;

    String[] styles = {"Standard", "Waite", "Red"};
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentMapBinding.inflate(inflater, container, false);
        init();
        checkReqPermission();
        return bd.getRoot();
    }

    private void init() {
        SupportMapFragment mapFragment = (SupportMapFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }else{
            Toast.makeText(requireContext(), "Null", Toast.LENGTH_SHORT).show();
        }

        adapter = new ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, styles);
        bd.spinner.setAdapter(adapter);

//        initEvent();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private boolean checkReqPermission() {
        int ACCESS_COARSE_LOCATION = ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
        );

        int ACCESS_FINE_LOCATION = ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
        );

        int ACCESS_BACKGROUND_LOCATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ACCESS_BACKGROUND_LOCATION = ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION
            );
        } else {
            ACCESS_BACKGROUND_LOCATION = PackageManager.PERMISSION_GRANTED;
        }

        if (ACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED ||
                ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, START_CODE);
            } else {
                enableMyLocation();
            }
        } else {
            enableMyLocation();
        }

        return ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED &&
                ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED;
    }

    private void initEvent() {
        bd.myLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "providerClient", Toast.LENGTH_SHORT).show();

            }
        });

        bd.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (bd.spinner.getSelectedItemPosition()) {
                    case 0:
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                requireContext(), R.raw.style_json3
                        ));
                        break;
                    case 1:
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                requireContext(), R.raw.style_json2
                        ));
                        break;
                    case 2:
                        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                                requireContext(), R.raw.style_json
                        ));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    Marker marker = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

//        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                requireContext(), R.raw.style_json3
        ));

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                if (marker != null)
                    marker.remove();
                marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Marker"));
            }
        });

        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        checkReqPermission();
    }

    private void enableMyLocation() {
        if (map != null) {
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(requireContext(), "Hi", Toast.LENGTH_SHORT).show();
        marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(
                        map.getMyLocation().getLatitude(),
                        map.getMyLocation().getLongitude()
                ))
                .title("Marker"));

        bd.saveBtn.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(requireContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }
}