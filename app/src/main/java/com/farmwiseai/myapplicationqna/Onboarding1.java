package com.farmwiseai.myapplicationqna;

import android.content.Intent;
import android.os.Bundle;
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
        setContentView(R.layout.onboarding1_activity);

        viewPager = findViewById(R.id.view_pager);
        indicatorLayout = findViewById(R.id.indicator_layout);

        getStartedButton = findViewById(R.id.get_started_button);
        skipButton = findViewById(R.id.skip_button);
        viewAdapter = new ViewAdapter(this);
        viewPager.setAdapter(viewAdapter);
        totalItems = viewAdapter.getCount();

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
        updateIndicators(20);
    }

    private void updateIndicators(int position) {
        indicatorLayout.removeAllViews();
        for (int i = 0; i < totalItems; i++) {
            View indicator = new View(this);
            indicator.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
            indicator.setBackgroundResource(i == position
                    ? R.drawable.selected_dot
                    : R.drawable.unselected_dot);
            indicatorLayout.addView(indicator);
        }
    }
}
