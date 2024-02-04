package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    EditText  otptext2,otptext3,otptext4,otptext5,otptext6,otptext7;
    Button submitbtn;
    String getotp_fire;
    String Ph_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         otptext2= findViewById(R.id.inputotp1);
        otptext3= findViewById(R.id.inputotp2);
        otptext4= findViewById(R.id.inputotp3);
        otptext5= findViewById(R.id.inputotp4);
        otptext6= findViewById(R.id.inputotp5);
        otptext7= findViewById(R.id.inputotp6);

        submitbtn=  findViewById(R.id.submitbtn);

        ProgressBar proges = findViewById(R.id.progressBar2);

         getotp_fire = getIntent().getStringExtra("otp_fire");
        Ph_no = getIntent().getStringExtra("Ph_no");
        onCreate();




        submitbtn.setOnClickListener(v -> {


            if(!otptext2.getText().toString().isEmpty() && !otptext3.getText().toString().isEmpty()&&!otptext4.getText().toString().isEmpty()&&!otptext5.getText().toString().isEmpty()&&!otptext6.getText().toString().isEmpty()&&!otptext7.getText().toString().isEmpty()){

                String codeOtp = otptext2.getText().toString()+
                        otptext3.getText().toString()+
                        otptext4.getText().toString()+
                        otptext5.getText().toString()+
                        otptext6.getText().toString()+
                        otptext7.getText().toString();

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
                                    Intent intent= new Intent(getApplicationContext(),registration.class);
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

    private void onCreate() {
        otptext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otptext2.toString().trim().isEmpty()){
                    otptext3.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otptext3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otptext4.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otptext4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otptext5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otptext5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otptext6.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otptext6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otptext7.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}