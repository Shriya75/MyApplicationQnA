package com.farmwiseai.myapplicationqna;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Onboarding2 extends AppCompatActivity {
    private Button getStartedButton;
    private Button skipButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding2_activity);


        getStartedButton = findViewById(R.id.get_started_button1);
        skipButton = findViewById(R.id.skip_button1);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Get Started" button click
                // Add your code to navigate to the next screen or perform an action
                // For example, you can start a new activity
                Intent intent = new Intent(Onboarding2.this, Onboarding3.class);
                 startActivity(intent);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Skip" button click
                // Add your code to skip the introduction and proceed
                 Intent intent = new Intent(Onboarding2.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}