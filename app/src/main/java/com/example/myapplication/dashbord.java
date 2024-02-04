package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dashbord extends AppCompatActivity {
   @SuppressLint("UseSwitchCompatOrMaterialCode")
   Switch switch1;

    String phone;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        switch1 =findViewById(R.id.switch1);





        phone = getIntent().getStringExtra("Ph_no");

        switch1.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
            if(isChecked){
                enableFeature();
            }
            else {
                disableFeature();
            }
        });





               

    }

    private void disableFeature() {
        DatabaseReference cont = FirebaseDatabase.getInstance().getReference("Patners");
        cont.child(phone).setValue( null);
        Toast.makeText(dashbord.this,"You are Offline",Toast.LENGTH_SHORT).show();
    }

    private void enableFeature() {
        DatabaseReference cont = FirebaseDatabase.getInstance().getReference("Patners");
        cont.child(phone).setValue( " Online");
        Toast.makeText(dashbord.this,"You are Online",Toast.LENGTH_SHORT).show();

    }


}