package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dashbord extends AppCompatActivity {
    Button butub, butub2;
    TextView mobno1;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        butub = findViewById(R.id.butub);
        butub2 = findViewById(R.id.butub2);
        mobno1 = findViewById(R.id.dastxt);

        phone = getIntent().getStringExtra("Ph_no");
        mobno1.setText(phone);


        butub.setOnClickListener(v -> onCreate());
        butub2.setOnClickListener(v-> onCreate2());
               

    }

    private void onCreate2() {
        DatabaseReference cont = FirebaseDatabase.getInstance().getReference("Patners");
        cont.child(phone).setValue( null);
        Toast.makeText(dashbord.this,"You are Offline",Toast.LENGTH_SHORT).show();


    }

    private void onCreate() {
        DatabaseReference cont = FirebaseDatabase.getInstance().getReference("Patners");
        cont.child(phone).setValue( " Online");
        Toast.makeText(dashbord.this,"You are Online",Toast.LENGTH_SHORT).show();

    }


}