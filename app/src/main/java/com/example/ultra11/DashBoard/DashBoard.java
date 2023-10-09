package com.example.ultra11.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ultra11.Adapters.MatchAdapter;
import com.example.ultra11.Adapters.YourMatchData;
import com.example.ultra11.R;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        recyclerView = findViewById(R.id.match_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Replace YourMatchData with actual match data objects
        List<YourMatchData> matchList = getMatchData(); // Retrieve your match data

        adapter = new MatchAdapter(matchList);
        recyclerView.setAdapter(adapter);
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