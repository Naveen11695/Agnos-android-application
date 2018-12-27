package naveen.agnosbeta.agnos;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

public class locate_us extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static TextView Username_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locate_us);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Username_about=(TextView)findViewById(R.id.locate_us_user_id);
        database_handler about=new database_handler();
        Username_about.setText(about.email_action_bar());
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


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            MapStyleOptions style =MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.map_morning);
            mMap.setMapStyle(style);
        }

        else if(timeOfDay >= 12 && timeOfDay < 16){
            MapStyleOptions style =MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.afternoon);
            mMap.setMapStyle(style);
        }

        else if(timeOfDay >= 16 && timeOfDay < 21){
            MapStyleOptions style =MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.map_evening);
            mMap.setMapStyle(style);
        }

        else if(timeOfDay >= 21 && timeOfDay < 24){
            MapStyleOptions style =MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.map_night);
            mMap.setMapStyle(style);
        }

        LatLng narang = new LatLng(28.714000, 77.10535);

        mMap.addMarker(new MarkerOptions().position(narang).title("AGNOS DEVELOPERS"+" Address:"+"B-5,364/365, Sector-5,Rohini, Delhi-110085 Ph:98737359735,8826843701"));
        float zoomLevel = 18.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(narang, zoomLevel));;
    }
}
