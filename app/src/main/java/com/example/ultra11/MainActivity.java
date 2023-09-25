package com.example.ultra11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private  static  int SPLASH_SCREEN = 5000;


    //Variables

    Animation topAnim,BottomAnim;

    ImageView image;

    TextView logo,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);














    }
}