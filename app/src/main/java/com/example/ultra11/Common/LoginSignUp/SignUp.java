package com.example.ultra11.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultra11.R;


public class SignUp extends AppCompatActivity {


//    Variables

    ImageView backBtn;

    Button next,login;

    TextView  titleText;







    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Hooks

        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);











    }

    public void CallnextSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp2Class.class);

        // Add transition
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arr_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login-btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
//options.toBundle()
        // Start the new activity with transition options
        startActivity(intent);
    }



}

