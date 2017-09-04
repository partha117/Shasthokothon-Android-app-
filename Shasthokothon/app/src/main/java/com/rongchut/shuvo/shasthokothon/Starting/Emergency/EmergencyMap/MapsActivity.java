package com.rongchut.shuvo.shasthokothon.Starting.Emergency.EmergencyMap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rongchut.shuvo.shasthokothon.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Location myloc;
    private  ArrayList<LocationData> location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.setRetainInstance(true);
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
        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);


        // Add a marker in Sydney and move the camera
        /*MyLocation loc=new MyLocation(this);
        LatLng sydney = new LatLng(loc.getLat(), loc.getLang());*/


        MyLocation myLocation = new MyLocation(MapsActivity.this, 1000) {
            @Override
            void onChange(final double lat, final double lang) {
                LatLng myloc = new LatLng(lat, lang);
                mMap.clear();
                //mMap.setMinZoomPreference(15);
                mMap.addMarker(new MarkerOptions().position(myloc).title("আপনার অবস্থান"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 15));
                final boolean[] previousStatus = {false};
                Timer t = new Timer();

                t.scheduleAtFixedRate(
                        new TimerTask() {
                            public void run() {
                                if (!previousStatus[0]) {
                                    if (haveNetworkConnection()) {
                                        previousStatus[0] = true;

                                        new MapTask() {
                                            @Override
                                            void finishJob(ArrayList<LocationData> data) {
                                                setData(data);
                                                for (int i = 0; i < data.size(); i++) {
                                                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.hospital);
                                                    MarkerOptions marker = new MarkerOptions().position(data.get(i)
                                                            .getLocation()).title(data.get(i).getName()
                                                    ).icon(icon);



                           /* Marker hospitalMarker=mMap.addMarker(new MarkerOptions().position(data.get(i).getLocation()).title(data.get(i).getName()));*/
                                                    Marker hospitalMarker = mMap.addMarker(marker);
                                                    hospitalMarker.setTag(data.get(i));
                                                }
                                            }
                                        }.execute(lat, lang, 5000, "hospital");

                                    } else {
                                        previousStatus[0] = false;
                                    }
                                }
                            }
                        },
                        0,      // run first occurrence immediatetly
                        2000);


            }
        };
    }
    private  void setData(ArrayList<LocationData> as)
    {
        location=as;

    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni!=null) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        LocationData myLocation= (LocationData) marker.getTag();
        if(myLocation!=null)
        {
            final Dialog dialog = new Dialog(MapsActivity.this);
            dialog.setContentView(R.layout.map_dialog);
            dialog.setTitle("ঠিকানা");

            // set the custom dialog components - text, image and button
            TextView name = (TextView) dialog.findViewById(R.id.name);
            TextView location = (TextView) dialog.findViewById(R.id.location);
            ImageView image = (ImageView) dialog.findViewById(R.id.image);
            image.setImageResource(R.mipmap.hospital);
            name.setText("নাম ঃ " + myLocation.getName());
            location.setText("ঠিকানা ঃ " + myLocation.getAddress());
            dialog.show();
        }


        return false;
    }
}
