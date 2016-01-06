package com.digitallight.u_gorescue;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import java.io.IOException;
import java.util.List;


public class Home extends Activity implements
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        View.OnClickListener {

    private Button ButtonPD;
    private GoogleMap mMap;
    private UiSettings mUiSetting;
    private GoogleApiClient mGoogleApiClient;

    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButtonPD = (Button) findViewById(R.id.pdButton);
        ButtonPD.setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pdButton:
                TampilLokasi();
//                Toast.makeText(getApplicationContext(), "Test Button", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void TampilLokasi(){
        if (mGoogleApiClient.isConnected()) {
            Location location = new Location(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
            double lat = location.getLatitude();
            double lang = location.getLongitude();
            List<Address> addressList = null;

            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocation(lat, lang, 1);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Lokasi tidak ada ", Toast.LENGTH_SHORT).show();
            }

            Address address = addressList.get(0);

            String msg = "Lokasiku di " + address.getSubLocality() + ", " +
                    address.getThoroughfare() + ", " + address.getSubAdminArea() +
                    " dengan koordinat lat : " + lat + " dan long : " + lang;
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
        }
    }
}
