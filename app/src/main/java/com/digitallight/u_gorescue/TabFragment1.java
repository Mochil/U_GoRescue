package com.digitallight.u_gorescue;

/**
 * Created by Pena Orange on 16/01/2016.
 */

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import java.io.IOException;
import java.util.List;

public class TabFragment1 extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, View.OnLongClickListener {

    private Button ButtonPD;
    private GoogleMap mMap;
    private UiSettings mUiSetting;
    private GoogleApiClient mGoogleApiClient;
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pangdar = inflater.inflate(R.layout.activity_home, container, false);

        ButtonPD = (Button) pangdar.findViewById(R.id.pdButton);
//        ButtonPD.setOnClickListener(this);
        ButtonPD.setOnLongClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        return pangdar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pdButton:
                tampilLokasi();
                break;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void tampilLokasi(){
        if (mGoogleApiClient.isConnected()) {
            Location location = new Location(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
            double lat = location.getLatitude();
            double lang = location.getLongitude();
            List<Address> addressList = null;

            Geocoder geocoder = new Geocoder(getActivity());
            try {
                addressList = geocoder.getFromLocation(lat, lang, 1);
            } catch (IOException e) {
                Toast.makeText(getActivity().getApplicationContext(), "Lokasi tidak ada ", Toast.LENGTH_SHORT).show();
            }

            Address address = addressList.get(0);

            String msg = "Lokasiku di " + address.getSubLocality() + ", " +
                    address.getThoroughfare() + ", " + address.getSubAdminArea() +
                    " dengan koordinat lat : " + lat + " dan long : " + lang;
            Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        tampilLokasi();
        return false;
    }
}
