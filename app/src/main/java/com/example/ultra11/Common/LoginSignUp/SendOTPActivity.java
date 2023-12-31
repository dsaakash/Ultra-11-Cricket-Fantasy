package com.example.ultra11.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ultra11.Common.LoginSignUp.VerifyOTPActivity;
import com.example.ultra11.R;
import com.hbb20.CountryCodePicker;

public class SendOTPActivity extends AppCompatActivity {


    CountryCodePicker ccp;
    EditText t1;

    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);


        t1 = (EditText) findViewById(R.id.t1);
        ccp= (CountryCodePicker) findViewById(R.id.ccp);

        ccp.registerCarrierNumberEditText(t1);

        b1 = (Button) findViewById(R.id.b1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendOTPActivity.this, VerifyOTPActivity.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace("",""));

                startActivity(intent);


            }
        });


    }
}