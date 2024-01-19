package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText mobno;

    Button otpbtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobno =  findViewById(R.id.mob_no);
        otpbtn= findViewById(R.id.otpbtn);
        ProgressBar prog = findViewById(R.id.progressBar);

        otpbtn.setOnClickListener(v -> {
            if(!mobno.getText().toString().trim().isEmpty()){
                if(mobno.getText().toString().trim().length()==10){

                    prog.setVisibility(View.VISIBLE);
                    otpbtn.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91"+ mobno.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    prog.setVisibility(View.GONE);
                                    otpbtn.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    prog.setVisibility(View.GONE);
                                    otpbtn.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();



                                }

                                @Override
                                public void onCodeSent(@NonNull String otp_fire, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(otp_fire, forceResendingToken);
                                    prog.setVisibility(View.GONE);
                                    otpbtn.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                                    intent.putExtra("otp_fire",otp_fire);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this,"OTP sent sucessfully",Toast.LENGTH_SHORT).show();


                                }
                            }

                    );



                }else{
                    Toast.makeText(MainActivity.this,"Enter a valid no.",Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(MainActivity.this,"Enter a mobile no.",Toast.LENGTH_SHORT).show();
            }
        });




    }
}