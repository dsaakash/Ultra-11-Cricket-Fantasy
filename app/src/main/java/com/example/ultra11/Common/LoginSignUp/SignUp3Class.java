package com.example.ultra11.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.ultra11.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3Class extends AppCompatActivity {

//    ScrollView scrollView;
    TextInputLayout phoneNumber;

    CountryCodePicker countryCodePicker;
    Button signup_next_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3_class);


//        scrollView = findViewById(R.id.signup_otp);
        countryCodePicker = findViewById(R.id.ccpi);
        phoneNumber = findViewById(R.id.phoneno);


        signup_next_button = findViewById(R.id.signup_next_button);



        signup_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Data from previous screens


                Intent intent  = new Intent(getApplicationContext(), VerifyOTP.class);

//                String _getUserEnteredPhoneNumber =  phoneNumber.getEditText().getText().toString().trim();
//                String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;

                // Pass data to next activity

              //  intent.putExtra("phoneNo",_phoneNo);




                startActivity(intent);


            }
        });




    }


//    public void CallVerifyOTPScreen(View view) {
//
////        Validate fields
//
//
//
//    }
}