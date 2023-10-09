package com.example.ultra11.Common.ReferalScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ultra11.DashBoard.DashBoard;
import com.example.ultra11.R;

public class ReferalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referal_screen);
    }


    public void NavigatetoDashBOard(View view) {

        startActivity(new Intent(getApplicationContext(), DashBoard.class));
    }
}