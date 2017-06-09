package com.example.eduardo.game;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    public static final String ARGUMENTO_TITLE = "TITLE";
    public static final String ARGUMENTO_FULL_SNIPPET = "FULL_SNIPPET";

    private String title;
    private String fullSnippet;
    private GoogleMap mMap;
    Button btnmaps, btnterreno, btnhibri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnmaps= (Button)findViewById(R.id.bntmap);
        btnterreno= (Button)findViewById(R.id.bntterreno);
        btnhibri=(Button)findViewById(R.id.btnhib) ;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnhibri.setOnClickListener(this);
        btnterreno.setOnClickListener(this);
        btnmaps.setOnClickListener(this);
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
        Marker markerArgentina;
        String nom;
        double a1,l1,a2,a3,l2,l3;

        Intent inDatos = getIntent();
        Bundle bnDatos = inDatos.getExtras();
        nom=bnDatos.getString("Nom");
        a1=bnDatos.getDouble("a1");
        l1=bnDatos.getDouble("l1");
        a2=bnDatos.getDouble("a2");
        l2=bnDatos.getDouble("l2");
        a3=bnDatos.getDouble("a3");
        l3=bnDatos.getDouble("l3");

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(a1, l1);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""+nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng Tib = new LatLng(a2, l2);
        mMap.addMarker(new MarkerOptions().position(Tib).title(""+nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Tib));

        LatLng Tib2 = new LatLng(a3, l3);
        mMap.addMarker(new MarkerOptions().position(Tib2).title(""+nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Tib2));




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bntmap:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btnhib:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.bntterreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }

    }
}
