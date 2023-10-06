package com.example.ultra11.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.ultra11.Common.ReferalScreen.ReferalScreen;
import com.example.ultra11.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pinfromUser;
    String CodeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);

        pinfromUser = findViewById(R.id.pin_view);


        String _phoneNo = getIntent().getStringExtra("phoneNo");


        sendVerificationCodeToUser(_phoneNo);





    }

    private void sendVerificationCodeToUser(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,
                60L,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD,
                mCallbacks
        );
    }
    private PhoneAuthProvider .OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    CodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    String code =  phoneAuthCredential.getSmsCode();

                    if (code!=null){

                        pinfromUser.setText(code);
                        verifyCode(code);

                    }

                    

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                    Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(CodeBySystem,code);
        signInWithPhoneAuthCredential(credential);
        
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(VerifyOTP.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(VerifyOTP.this, ReferalScreen.class));
                            finish();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyOTP.this, "Verification Not Completed! Try again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    public void callNextScreenFromOTP(View view) {

        // After getting otp navigating to Dashboard
            String  code = pinfromUser.getText().toString();

            if (!code.isEmpty()){

                verifyCode(code);

            }
    }
}