package com.example.ultra11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.ultra11.DashBoard.DashBoard;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Phone_Login_Screen extends AppCompatActivity {


    private CountryCodePicker ccp;
    private EditText phoneet;

    private PinView firstPinView;
    private String selected_country_code = "+91";

    private static final int CREDENTIAL_PICKER_REQUEST =120 ;

    private ConstraintLayout phoneLayout;
    private ProgressBar progressBar;


    // ---------------------------FIREBASE PHONE AUTH --------------------------------------//

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResentToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login_screen);

        ccp = findViewById(R.id.ccp);
        phoneet =findViewById(R.id.phoneet);
        firstPinView =findViewById(R.id.firstPinView);
        phoneLayout =findViewById(R.id.phoneLayout);
        progressBar = findViewById(R.id.progressBar);

        mAuth  = FirebaseAuth.getInstance();







        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                selected_country_code = ccp.getSelectedCountryCodeWithPlus();

            }
        });

        phoneet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length()==10){
                    sendotp();



                   // Toast.makeText(Phone_Login_Screen.this, "10 digit mobile", Toast.LENGTH_SHORT).show();
//                    phoneLayout.setVisibility(View.GONE);
//                    firstPinView.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


//        PIN VIEW

        // pin view

        firstPinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()==6){


                    progressBar.setVisibility(View.VISIBLE);


                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,firstPinView.getText().toString().trim());
                    signInWithAuthCredential(credential);

                   // Toast.makeText(Phone_Login_Screen.this, "6 digit pin", Toast.LENGTH_SHORT).show();
//                    phoneLayout.setVisibility(View.GONE);
//                    firstPinView.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {



            }
        });

//        Auto select phone api


        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();


        PendingIntent intent = Credentials.getClient(Phone_Login_Screen.this).getHintPickerIntent(hintRequest);
        try
        {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0,new Bundle());
        }
        catch (IntentSender.SendIntentException e)
        {
            e.printStackTrace();
        }



//        ------------------ otp Call Backs ---------------------------//

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                String code = phoneAuthCredential.getSmsCode();

                if (code !=null){
                    firstPinView.setText(code);
                    signInWithAuthCredential(phoneAuthCredential);
                }

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Phone_Login_Screen.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
                phoneLayout.setVisibility(View.VISIBLE);
                firstPinView.setVisibility(View.GONE);

            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, token);
                mVerificationId = verificationId;
                mResentToken = token;

                Toast.makeText(Phone_Login_Screen.this, "  6  digit otp code sent", Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
                phoneLayout.setVisibility(View.GONE);
                firstPinView.setVisibility(View.VISIBLE);




            }
        };



    }



    private void sendotp() {

        progressBar.setVisibility(View.VISIBLE);



        String phoneNumber = selected_country_code+phoneet.getText().toString();
        PhoneAuthOptions  options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setPhoneNumber(phoneNumber)
                        .setActivity(Phone_Login_Screen.this)
                        .setCallbacks(callbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == RESULT_OK)
        {
            // Obtain the phone number from the result
            Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);
            /* EditText.setText(credentials.getId().substring(3));*/ //get the selected phone number
//Do what ever you want to do with your selected phone number here


            phoneet.setText(credentials.getId().substring(3));


        }

        else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE)
        {
            // *** No phone numbers available ***
            Toast.makeText(Phone_Login_Screen.this, "No phone numbers found", Toast.LENGTH_LONG).show();
        }
    }

    private void signInWithAuthCredential(PhoneAuthCredential credentials) {

    mAuth.signInWithCredential(credentials)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()){
                        Toast.makeText(Phone_Login_Screen.this, "Logged in Sucessfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Phone_Login_Screen.this, DashBoard.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(Phone_Login_Screen.this, "Login failed", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Phone_Login_Screen.this, Phone_Login_Screen.class);
                        startActivity(intent);
                        finish();



                    }

                }
            });






    }
}