package com.example.ultra11.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultra11.R;

public class SignUp2Class extends AppCompatActivity {

    //    Variables

    ImageView backBtn;

    Button next,login;

    TextView titleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2_class);


        //Hooks

        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
            }
        });



    }

    public void CallnextSignUpScreen(View view){

        Intent intent = new Intent(getApplicationContext(), SignUp3Class.class);

        // Add transition

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arr_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login-btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");


       // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2Class.this,pairs);


        startActivity(intent);





    }

    public void CallPreviousScreen(View view) {

        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }
}