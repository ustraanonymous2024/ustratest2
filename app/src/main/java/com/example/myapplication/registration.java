package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class registration extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final String TAG = "registration";

    private static final String name0 = "name2";
    private static final String saloon_Name0 = "saloon_Name2";
    private static final String Seat0 = "Seat2";
    private static final String Acard0 = "Acard2";
    private static final String Pcard0 = "Pcard2";




    EditText name1 ,saloonName1,Seat1,Acard1,Pcard1;
    Button register1 , locationbtn;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static int REQUEST_CODE = 100;
    double lattiude,longitude;
    String addline ,city,country;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name1 = findViewById(R.id.name);
        saloonName1 = findViewById(R.id.saloonName);
        Seat1 = findViewById(R.id.Seat);
        Acard1= findViewById(R.id.Acard);
        Pcard1 = findViewById(R.id.Pcard);
        register1 = findViewById(R.id.register);
        //locationa ka kaam
        locationbtn = findViewById(R.id.locationbtn);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);




        register1.setOnClickListener(v-> onCreate());
        locationbtn.setOnClickListener(v-> onCreate2());

    }

    private void onCreate2() {
        //yeh loaction wala hai
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {

                    if (location != null){
                        Geocoder geocoder = new Geocoder(registration.this, Locale.getDefault());

                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            assert addresses != null;
                            lattiude = addresses.get(0).getLatitude();
                             longitude = addresses.get(0).getLongitude();
                             addline = addresses.get(0).getAddressLine(0);
                             city = addresses.get(0).getLocality();
                             country = addresses.get(0).getCountryName();





                        } catch (IOException e) {
                            throw new RuntimeException(e);


                        }


                    }


            });
        }else {
            askPermission();
        }

    }

    private void askPermission() {
        ActivityCompat.requestPermissions(registration.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
            onCreate2();
        }
        else{
            Toast.makeText(registration.this,"Please Turn Onn Your GPS",Toast.LENGTH_SHORT).show();

        }
    }

    private void onCreate() {
        // EditText ko variable meh store kraya hu
        String  Name = name1.getText().toString();
        String  Saloon_Name = saloonName1.getText().toString();
        String  Seat = Seat1.getText().toString();
        String  Adhar_card = Acard1.getText().toString();
        String  PAN_card = Pcard1.getText().toString();
//yah pe hash map ka use kiya hu
        Map<String,Object> vat = new HashMap<>();
        vat.put(name0,Name);
        vat.put(saloon_Name0,Saloon_Name);
        vat.put(Seat0,Seat);
        vat.put(Acard0,Adhar_card);
        vat.put(Pcard0,PAN_card);





//yah pe saloon ke naam se data base banaya hu
        String uniqueCollectionId = generateUniqueCollectionId();
        CollectionReference uniqueCollectionRef = db.collection(uniqueCollectionId);

        uniqueCollectionRef.add(vat)
                .addOnSuccessListener(unused -> Toast.makeText(registration.this, "sucessful", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> {
                    Toast.makeText(registration.this, e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG,e.toString() );


                });
    }

    private String generateUniqueCollectionId() {
        return saloonName1.getText().toString() ;
        //yeh saloon ke naam se genrate krne ka function hai
    }







}