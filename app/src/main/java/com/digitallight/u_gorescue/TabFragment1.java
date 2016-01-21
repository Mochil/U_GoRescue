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

import com.digitallight.u_gorescue.rest.PangdarApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TabFragment1 extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener, View.OnLongClickListener {

    //Root URL of our web service
    public static final String ROOT_URL = "http://developsalis.esy.es/";

    private Button ButtonPD;
    private GoogleMap mMap;
    private UiSettings mUiSetting;
    private GoogleApiClient mGoogleApiClient;
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pangdar = inflater.inflate(R.layout.activity_home, container, false);

        ButtonPD = (Button) pangdar.findViewById(R.id.pdButton);
        ButtonPD.setOnLongClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        return pangdar;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.pdButton:
//                tampilLokasi();
//                break;
//        }
//    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void tampilLokasi(){
        try {
            if (mGoogleApiClient.isConnected()) {
                Location location = new Location(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
                double lat = location.getLatitude();
                double lang = location.getLongitude();
                List<Address> addressList = null;

                Geocoder geocoder = new Geocoder(getActivity());
                try {
                    addressList = geocoder.getFromLocation(lat, lang, 1);
                } catch (IOException e) {
//                    Toast.makeText(getActivity().getApplicationContext(), "Lokasi tidak ada ", Toast.LENGTH_SHORT).show();
                }

                Address address = addressList.get(0);

                /*=================================================================*/
                //Here we will handle the http request to insert user to mysql db
                //Creating a RestAdapter
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ROOT_URL) //Setting the Root URL
                        .build(); //Finally building the adapter
//
//                //Creating object for our interface
                PangdarApi api = adapter.create(PangdarApi.class);
//
//                //Defining the method insertuser of our interface
//
                String idPanggilanDarurat;
                String tanggal;
                String sopir;
                String user;

                api.insertPangdar(
//                        //Passing the values by getting it from editTexts
//
                        idPanggilanDarurat = null,
                        tanggal = getDateTime(),
                        lat,
                        lang,
                        address.getSubLocality(),
                        address.getThoroughfare(),
                        address.getSubAdminArea(),
                        sopir = null,
                        user = "2",


                        //Creating an anonymous callback
                        new Callback<Response>() {
                            @Override
                            public void success(Response result, Response response) {
                                //On success we will read the server's output using bufferedreader
                                //Creating a bufferedreader object
                                BufferedReader reader = null;

                                //An string to store output from the server
                                String output = "";

                                try {
                                    //Initializing buffered reader
                                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                    //Reading the output in the string
                                    output = reader.readLine();
//                                    Toast.makeText(getActivity(), output, Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                //If any error occured displaying the error as toast
                                Toast.makeText(getActivity(), "Gagal",Toast.LENGTH_LONG).show();
                            }
                        }
                );


                /*==================================*/

//                String msg = "tanggal" + getDateTime()+"Lokasiku di " + address.getSubLocality() + ", " +
//                        address.getThoroughfare() + ", " + address.getSubAdminArea() +
//                        " dengan koordinat lat : " + lat + " dan long : " + lang;
                Toast.makeText(getActivity().getApplicationContext(), "Panggilan Darurat telah dikirim", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "Panggilan Darurat gagal dikirim,Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
