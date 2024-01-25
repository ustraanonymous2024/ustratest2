package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity2 extends AppCompatActivity {
    EditText otptext ;
    Button submitbtn;
    String getotp_fire;
    String Ph_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        otptext= findViewById(R.id.editText);
        submitbtn=  findViewById(R.id.submitbtn);

        ProgressBar proges = findViewById(R.id.progressBar2);

         getotp_fire = getIntent().getStringExtra("otp_fire");
        Ph_no = getIntent().getStringExtra("Ph_no");




        submitbtn.setOnClickListener(v -> {
            if(!otptext.getText().toString().isEmpty()){
                String codeOtp = otptext.getText().toString();

                if(getotp_fire!=null ){
                    proges.setVisibility(View.VISIBLE);
                    submitbtn.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential otpcred = PhoneAuthProvider.getCredential(
                            getotp_fire ,codeOtp
                    );
                    FirebaseAuth.getInstance().signInWithCredential(otpcred)
                            .addOnCompleteListener(task -> {
                                proges.setVisibility(View.GONE);
                                submitbtn.setVisibility(View.VISIBLE);
                                if(task.isSuccessful()){
                                    Intent intent= new Intent(getApplicationContext(),dashbord.class);
                                    intent.putExtra("Ph_no",Ph_no);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(MainActivity2.this,"Enter the Correct OTP ",Toast.LENGTH_SHORT).show();


                                }
                            });





                }else{
                    Toast.makeText(MainActivity2.this,"Please Check Intenet connection ",Toast.LENGTH_SHORT).show();

                }

                        Toast.makeText(MainActivity2.this,"Verifying OTP",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity2.this,"Please Enter a valid OTP",Toast.LENGTH_SHORT).show();
            }
        });
    }
}