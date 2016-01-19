package com.digitallight.u_gorescue;

/**
 * Created by Pena Orange on 16/01/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class TabFragment2 extends Fragment{
    private MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View map = inflater.inflate(R.layout.activity_maps, container, false);
        mMapView = (MapView) map.findViewById(R.id.mapview);

        // inflat and return the layout
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        MapsInitializer.initialize(getActivity());
        final GoogleMap googleMap = mMapView.getMap();
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //Enable GPS
        googleMap.setMyLocationEnabled(true);
        GPSService gps = new GPSService(getActivity());
        gps.getLocation();
        if (!gps.isLocationAvailable) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();

        } else {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            LatLng position = new LatLng(latitude,longitude);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 14);
            googleMap.animateCamera(update);
            mMapView.onResume();
        }
        return map;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /*
     * Using a mapview in a fragment requires you to 'route'
     * the lifecycle events of the fragment to the mapview
     */
    @Override
    public void onResume() {
        super.onResume();
        if (null != mMapView)
            mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mMapView)
            mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mMapView)
            mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != mMapView)
            mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (null != mMapView)
            mMapView.onLowMemory();
    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View Mmap = inflater.inflate(R.layout.activity_maps, container, false);
//        return Mmap;
//    }
}
