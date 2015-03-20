package com.example.paulo.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.paulo.agenda.model.Contato;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

    Contato contato;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        contato = (Contato)getIntent().getSerializableExtra("contato");
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        fragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        configMap();
        String[] locais = {"Estação Santa Efigenia, Metro BH",
                            "Estação Central, Metro BH",
                            "Estação Santa Tereza, Metro BH",
                            "Estação Horto, Metro BH",
                            "Estação Santa Inês, Metro BH",
                            "Estação José Candido da Silveira, Metro BH",
                            "Estação Minas Shopping, Metro BH"

                          };
        addContato(locais);
    }

    private void addContato(String[] locais){

        for(String local : locais) {
            Geocoder geocoder = new Geocoder(this);
            List<Address> result = null;
            try {
                result = geocoder.getFromLocationName(local, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //LatLng latLng = new LatLng(contato.getLatitude(),contato.getLongitude());
            LatLng latLng = new LatLng(result.get(0).getLatitude(), result.get(0).getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(local);
            //markerOptions.snippet(contato.getAddress());
            markerOptions.position(latLng);

            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }
    }

    private void configMap(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        UiSettings settings = googleMap.getUiSettings();

        settings.setAllGesturesEnabled(true);
        settings.setCompassEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setMyLocationButtonEnabled(true);
    }
}
