package com.example.ultra11.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ultra11.Adapters.CarouselAdapter;
import com.example.ultra11.Adapters.MatchAdapter;
import com.example.ultra11.Adapters.YourMatchData;
import com.example.ultra11.EditProfileScreen;
import com.example.ultra11.R;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private ImageView account_icon;
   // private CarouselAdapter carouselAdapter;
   // private ViewPager2 imageCarousel; // Use ViewPager here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        ImageSlider imageSlider = findViewById(R.id.imageSlider);


        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image, ScaleTypes.FIT));

        slideModels.add(new SlideModel(R.drawable.image_a,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_b,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_c,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_d,ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);




        // Initialize UI elements
        account_icon = findViewById(R.id.account_icon);
    //    imageCarousel = findViewById(R.id.image_carousel); // Initialize ViewPager here
        recyclerView = findViewById(R.id.match_recycler_view);


        // Set up image carousel (ViewPager)
//        List<Integer> imageList = new ArrayList<>();
//        imageList.add(R.drawable.image);
//        imageList.add(R.drawable.image_a);
//        imageList.add(R.drawable.img_b);
//        imageList.add(R.drawable.img_c);
//        imageList.add(R.drawable.img_d);

  //      carouselAdapter = new CarouselAdapter(this, imageList);

        // Set the adapter for ViewPager
//        imageCarousel.setAdapter(carouselAdapter);

        // Set up click listener for the account icon
        account_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfileScreen.class);
                startActivity(intent);
            }
        });

        // Set up the RecyclerView for match data
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<YourMatchData> matchList = getMatchData();
        adapter = new MatchAdapter(matchList);
        recyclerView.setAdapter(adapter);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); // or HORIZONTAL
        layoutManager.setReverseLayout(false); // or true if needed
        recyclerView.setLayoutManager(layoutManager);
    }

    // Replace this method with code to retrieve your match data
    private List<YourMatchData> getMatchData() {
        List<YourMatchData> matchList = new ArrayList<>();
        // Populate matchList with your match data
        // Sample match data
       matchList.add(new YourMatchData("IC IC World CUP", "October 5, 2023", "12:00 PM", R.drawable.india, R.drawable.pak,"₹60,000"));
        matchList.add(new YourMatchData("Premier League", "October 6, 2023", "2:30 PM", R.drawable.england, R.drawable.spain,"₹100,000"));
        matchList.add(new YourMatchData("Champions League", "October 7, 2023", "3:45 PM", R.drawable.italy, R.drawable.germany,"₹5,000"));
        matchList.add(new YourMatchData("La Liga", "October 8, 2023", "5:00 PM", R.drawable.spain, R.drawable.france,"₹10,000"));
        matchList.add(new YourMatchData("Serie A", "October 9, 2023", "4:15 PM", R.drawable.italy, R.drawable.england,"₹12,000"));
        matchList.add(new YourMatchData("Bundesliga", "October 10, 2023", "6:30 PM", R.drawable.germany, R.drawable.pak,"₹20,000"));
        matchList.add(new YourMatchData("Eredivisie", "October 11, 2023", "7:15 PM", R.drawable.netherlands, R.drawable.portugal,"₹30,000"));
        matchList.add(new YourMatchData("Primeira Liga", "October 12, 2023", "8:00 PM", R.drawable.portugal, R.drawable.italy,"₹40,000"));
        matchList.add(new YourMatchData("Major League Soccer", "October 13, 2023", "9:30 PM", R.drawable.usa, R.drawable.canada,"₹50,000"));
        matchList.add(new YourMatchData("Ligue 1", "October 14, 2023", "10:15 PM", R.drawable.france, R.drawable.spain,"₹80,000"));




     //   matchList.add(new YourMatchData("Match 2", "October 6, 2023", "2:30 PM", R.drawable.team3, R.drawable.team4));
        // Add more matches as needed

        return matchList;
    }


}