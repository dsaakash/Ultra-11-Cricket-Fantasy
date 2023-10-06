package com.example.ultra11.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ultra11.HelperClasses.SliderAdapter;
import com.example.ultra11.R;
import com.example.ultra11.Common.LoginSignUp.SignUp3Class;

public class OnBoarding extends AppCompatActivity {

// Create hooks


        ViewPager viewPager;
        LinearLayout dotslayout;

        TextView[]dots;


        Button letsGetStarted;

        SliderAdapter sliderAdapter;

        Animation animation;

        int CurrentPosition;

    // Override the onCreate method of the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        setContentView(R.layout.activity_on_boarding);





        // Initialize and hook up views from the layout.
        viewPager = findViewById(R.id.slider);      // Reference to ViewPager in the layout.
        dotslayout = findViewById(R.id.dots);        // Reference to the layout where dots will be displayed.
        letsGetStarted = findViewById(R.id.get_started_btn); // References for button




        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp3Class.class));
            }
        });

        // Create a SliderAdapter instance and set it as the adapter for the ViewPager.
        sliderAdapter = new SliderAdapter(this);      // Create a custom adapter for the ViewPager.
        viewPager.setAdapter(sliderAdapter);         // Set the adapter for the ViewPager.

        // Add initial dots and set up a page change listener.
        addDots(0);                                  // Add dots for the initial page.
        viewPager.addOnPageChangeListener(changeListener);  // Attach the onPageChangeListener.
    }



    public void skip(View view){

        startActivity(new Intent(getApplicationContext(), SignUp3Class.class));

        finish();

    }

    public void next(View view){


        viewPager.setCurrentItem(CurrentPosition+1);





    }



    private void addDots(int position) {
        dots = new TextView[4]; // Change the size according to the number of pages in your ViewPager.

        // Remove any existing views from the layout.
        dotslayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;")); // Use "&#8226;" for a bullet point (dot).
            dots[i].setTextSize(35);

            // Set the color of the dots (you can customize this part).
            dots[i].setTextColor(getResources().getColor(R.color.black));

            dotslayout.addView(dots[i]);
        }

        // Set the color of the currently selected dot.
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.yellow));
        }
    }




    // Create a ViewPager.OnPageChangeListener to track changes in the ViewPager.
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {

        // This method is called when the user scrolls through the pages.
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // Do nothing in this method.
            // You can add code here if you need to perform actions during scrolling.
        }

        // This method is called when a new page is selected.
        @Override
        public void onPageSelected(int position) {
            // Call the addDots() method to update the dots based on the selected page.
            addDots(position);

            CurrentPosition = position;

            if (position == 0){

                letsGetStarted.setVisibility(View.INVISIBLE);



            }
            else if (position == 1) {


                letsGetStarted.setVisibility(View.INVISIBLE);

            }
            else if (position == 2) {


                letsGetStarted.setVisibility(View.INVISIBLE);

            }

            else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);

            }
        }

        // This method is called when the scroll state changes (e.g., idle, dragging, settling).
        @Override
        public void onPageScrollStateChanged(int state) {
            // Do nothing in this method.
            // You can add code here if you need to perform actions when the scroll state changes.
        }
    };

}