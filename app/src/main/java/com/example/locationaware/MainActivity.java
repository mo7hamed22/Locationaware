package com.example.locationaware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button getloctionbtn;
    LocationManager mgr;
    MyloctionListner Listener;
    String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final int _requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Listener =new MyloctionListner();
        getloctionbtn = findViewById(R.id.get_location_btn);
        getloctionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we can call func that make location update
                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
//                    Toast.makeText(MainActivity.this,"one ckeck"
//                            ,Toast.LENGTH_LONG).show();
                    mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Listener);

                    //this for ask about location permisions
                  ActivityCompat.requestPermissions(MainActivity.this,permissions,_requestCode);
                    // here to request the missing permissions, and then overriding
                     //public void onRequestPermissionsResult( requestCode,  permissions,
                       //                                       int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                }else
                mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Listener);
                Toast.makeText(MainActivity.this,"Two ckeck"
                        ,Toast.LENGTH_LONG).show();

            }
        });
    }
    class MyloctionListner implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
        //here gets the location
            Log.d("Loction", "onLocationChanged: ");
            Toast.makeText(MainActivity.this,"Latitude "+location.getLatitude()+"\n"+"longtitude "+
                    location.getLongitude(),Toast.LENGTH_LONG).show();
         //  mgr.removeUpdates(Listener);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
    public void onRequestPermissionsResult( int requestCode, String[] permissionss, int[] grantResults)
    {
        switch (requestCode) {
            case _requestCode:
                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //this for ask about location permissions
                    Toast.makeText(MainActivity.this,"can't Get Location",Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this,permissions, requestCode);

                } else {
                    mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Listener);
                    Toast.makeText(MainActivity.this,"three ckeck"
                            ,Toast.LENGTH_LONG).show();
                }
        }

        }
 }

