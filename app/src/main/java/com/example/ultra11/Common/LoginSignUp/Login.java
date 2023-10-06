package com.example.ultra11.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultra11.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button CallSignUp,login_btn;
    ImageView image;

    TextView logoText,sloganText;
    TextInputLayout username,password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

//        CallSignUp =  findViewById(R.id.signup);
//        image =  findViewById(R.id.logoImage);
//        logoText =  findViewById(R.id.signup);
//        sloganText =  findViewById(R.id.slogan_name);
//        username =  findViewById(R.id.username);
//        password =  findViewById(R.id.password);
//        login_btn =  findViewById(R.id.login);


//        CallSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent  =  new Intent(Login.this, SignUp.class);
//                startActivity(intent);
//            }
//        });


        CallSignUp = findViewById(R.id.create_btn);
        login_btn = findViewById(R.id.signup_login_button);


//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Login.class));
//            }
//        });

        CallSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


    }
}