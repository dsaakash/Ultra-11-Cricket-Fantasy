package com.example.ultra11.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultra11.Common.LoginSignUp.SignUp3Class;
import com.example.ultra11.R;
import com.example.ultra11.Common.LoginSignUp.SendOTPActivity;

public class SplashScreen extends AppCompatActivity {



    private  static  int SPLASH_SCREEN = 5000;


    //Variables

    Animation topAnim,BottomAnim;

    ImageView image;

    TextView logo,slogan;

    SharedPreferences OnBoardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        BottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        // Adding Hooks

        image =  findViewById(R.id.imageView);
        logo  =  findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);


//        Adding animation to image to top animation

        image.setAnimation(topAnim);
        logo.setAnimation(BottomAnim);
        slogan.setAnimation(BottomAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OnBoardingScreen = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);

                boolean isFirstTime = OnBoardingScreen.getBoolean("firstTime",true);


                if (isFirstTime){

                    SharedPreferences.Editor editor = OnBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();


                    Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
//                startActivity(intent);
//                finish();
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(image,"logo_image");
                    pairs[1] = new Pair<View,String>(logo,"logo_text");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                    startActivity(intent,options.toBundle());


                }
                else {

                    Intent intent = new Intent(SplashScreen.this, SendOTPActivity.class);
//                startActivity(intent);
//                finish();
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(image,"logo_image");
                    pairs[1] = new Pair<View,String>(logo,"logo_text");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                    startActivity(intent,options.toBundle());


                }




            }
        },SPLASH_SCREEN);

    }
}