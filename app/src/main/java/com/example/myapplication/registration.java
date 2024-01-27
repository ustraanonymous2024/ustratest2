package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    EditText name ,saloonName,Seat,Acard,Pcard;
    Button register;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        saloonName = findViewById(R.id.saloonName);
        Seat = findViewById(R.id.Seat);
        Acard = findViewById(R.id.Acard);
        Pcard = findViewById(R.id.Pcard);
        register = findViewById(R.id.register);


        register.setOnClickListener(v-> onCreate());





    }

    private void onCreate() {
        Map<String, String> vat = new HashMap<>();
        vat.put("name", name.getText().toString());
        vat.put("saloon Name", saloonName.getText().toString());
        vat.put("Seat", Seat.getText().toString());
        vat.put("Acard", Acard.getText().toString());
        vat.put("Pcard", Pcard.getText().toString());

            // Generate a unique collection ID
            String uniqueCollectionId = generateUniqueCollectionId();

            // Create a reference to the dynamically generated collection
            CollectionReference uniqueCollectionRef = db.collection(uniqueCollectionId);

            // Add data to the dynamically generated collection

            uniqueCollectionRef.add(vat)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Data successfully added to the unique collection
                            Log.d("Firestore", "Data added to unique collection!");
                        } else {
                            // Handle errors here
                            Log.w("Firestore", "Error adding data to unique collection", task.getException());
                        }
                    });





///ssdsds

    }


    //sss
    private String generateUniqueCollectionId() {
        return saloonName.getText().toString();
    }



}