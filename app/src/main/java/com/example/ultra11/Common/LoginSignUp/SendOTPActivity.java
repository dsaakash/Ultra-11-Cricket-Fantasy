package com.example.ultra11.Common.LoginSignUp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultra11.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hbb20.CountryCodePicker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class SendOTPActivity extends AppCompatActivity {

    EditText t1;
    Button b1;

    CountryCodePicker ccp;



    private GoogleApiClient googleApiClient;
    private static final int PHONE_HINT = 1008;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);

        t1 = findViewById(R.id.t1);


        ccp= (CountryCodePicker) findViewById(R.id.ccp);

        ccp.registerCarrierNumberEditText(t1);

        // Create a GoogleApiClient object.
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t1.getText().toString().isEmpty()) {
                    // Request the user's phone number from the Google Play services credentials API
                    HintRequest hintRequest = new HintRequest.Builder()
                            .setPhoneNumberIdentifierSupported(true)
                            .build();
                    PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
                    try {
                        startIntentSenderForResult(intent.getIntentSender(), PHONE_HINT, null, 0, 0, 0);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b1 = findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = t1.getText().toString();
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(SendOTPActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                } else {
                    // Include the country code when passing to VerifyOTPActivity
                    Intent intent = new Intent(SendOTPActivity.this, VerifyOTPActivity.class);
                    intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace("",""));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHONE_HINT && resultCode == RESULT_OK) {
            // Get the user's phone number from the intent.
            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
            String phoneNumber = credential.getId();

            // Remove the country code and prefix
            phoneNumber = removeCountryCodeAndPrefix(phoneNumber);

            // Display the phone number in the EditText
            t1.setText(phoneNumber);
        }
    }

    private String removeCountryCodeAndPrefix(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, null);
            int countryCode = parsedPhoneNumber.getCountryCode();
            String nationalSignificantNumber = phoneNumberUtil.getNationalSignificantNumber(parsedPhoneNumber);
            return nationalSignificantNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return phoneNumber; // In case of an error, return the original number
        }
    }
}
