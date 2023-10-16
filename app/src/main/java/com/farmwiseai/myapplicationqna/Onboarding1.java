package com.farmwiseai.myapplicationqna;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Onboarding1 extends AppCompatActivity {
    ViewPager viewPager;
    ViewAdapter viewAdapter;

    private Button getStartedButton;
    private Button skipButton;
    private LinearLayout indicatorLayout;
    private int totalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding1_activity); // Make sure you have the appropriate layout XML

        viewPager = findViewById(R.id.view_pager);
        indicatorLayout = findViewById(R.id.indicator_layout);

        getStartedButton = findViewById(R.id.get_started_button);
        skipButton = findViewById(R.id.skip_button);
        viewAdapter = new ViewAdapter(this); // You should have a custom ViewAdapter class
        viewPager.setAdapter(viewAdapter);
        totalItems = viewAdapter.getCount(); // Get the total number of items in your adapter

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Get Started" button click
                Intent intent = new Intent(Onboarding1.this, Onboarding2.class);
                startActivity(intent);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Skip" button click
                Intent intent = new Intent(Onboarding1.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Do nothing
            }

            @Override
            public void onPageSelected(int position) {
                // Update the indicators
                updateIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing
            }
        });

        // Create and update indicators
        updateIndicators(0); // Initialize indicators with the first page

        // Set up automatic sliding
        Handler handler = new Handler();
        Runnable autoSlideRunnable = new Runnable() {
            @Override
            public void run() {
                int nextItem = viewPager.getCurrentItem() + 1;
                if (nextItem >= viewPager.getAdapter().getCount()) {
                    nextItem = 0; // Wrap around to the first item if at the end
                }
                viewPager.setCurrentItem(nextItem, true); // Set true for smooth scroll
                handler.postDelayed(this, 3000); // Change slide interval as needed (e.g., 3000 milliseconds)
            }
        };

        // Start automatic sliding after a delay
        handler.postDelayed(autoSlideRunnable, 3000); // Start sliding after a delay (e.g., 3000 milliseconds)
    }

    private void updateIndicators(int position) {
        indicatorLayout.removeAllViews();
        for (int i = 0; i < totalItems; i++) {
            View indicator = new View(this);
            indicator.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
            indicator.setBackgroundResource(i == position
                    ? R.drawable.selected_dot // Use your selected_dot drawable resource
                    : R.drawable.unselected_dot); // Use your unselected_dot drawable resource
            indicatorLayout.addView(indicator);
        }
    }
}
