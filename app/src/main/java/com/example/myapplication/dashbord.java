package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        return false;
    }
}


public class dashbord extends AppCompatActivity {
    Button butub,
     butub2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        butub = findViewById(R.id.butub);
        butub2 = findViewById(R.id.butub2);

        butub.setOnClickListener(v -> onCreate());

        butub2.setOnClickListener(v -> onCreate2());





}

    private void onCreate2() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("https://console.firebase.google.com/u/0/project/ustramerchant/database/ustramerchant-default-rtdb/data/~2F");
        myRef.removeValue();
    }

    private void onCreate() {

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("internet_status");

        if (NetworkUtils.isNetworkAvailable(this)) {
            databaseReference.setValue("Online");
            Toast.makeText(dashbord.this,"You are Online now ",Toast.LENGTH_SHORT).show();

        } else {
            databaseReference.setValue("Offline");
            Toast.makeText(dashbord.this,"Please Check Internet Connection ",Toast.LENGTH_SHORT).show();
//agcfgcfgfvx
        }
    }
}
