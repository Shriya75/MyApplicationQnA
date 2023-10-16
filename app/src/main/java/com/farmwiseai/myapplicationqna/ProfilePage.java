package com.farmwiseai.myapplicationqna;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 124;
    private ImageView selectedImageView;
    private ImageButton centerImageButton;
    private EditText text_area;
    private Button SaveButton;
    private TextView uploadPictureText;
    private TextView uploadPictureText1; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        selectedImageView = findViewById(R.id.selected_image_view);
        centerImageButton = findViewById(R.id.center_image_button);
        text_area = findViewById(R.id.text_area);
        SaveButton = findViewById(R.id.SavedButton);
        uploadPictureText = findViewById(R.id.text_view_1);
        uploadPictureText1 = findViewById(R.id.text_view_2);// Initialize the TextView

        centerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });

        text_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the hint text when EditText is clicked
                text_area.setHint("");
            }
        });

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the input text is empty
                String name = text_area.getText().toString().trim();
                if (name.isEmpty() || name.equals("Enter your name")) {
                    // Display a warning toast message
                    Toast.makeText(getApplicationContext(), "Please enter a name.", Toast.LENGTH_LONG).show();
                } else {
                    // Save the name to SharedPreferences
                    saveNameToSharedPreferences(name);

                    // Input is not empty, proceed to the next screen
                    Intent intent = new Intent(ProfilePage.this, Screen1.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            // Handle the selected image using the content URI (data.getData).
            Uri imageUri = data.getData();

            // Set the image as the background of the circular ImageView
            selectedImageView.setImageURI(imageUri);
            selectedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Save the image URI to SharedPreferences
            saveImageUriToSharedPreferences(imageUri);

            // Hide the ImageButton and the "Upload your picture" text
            centerImageButton.setVisibility(View.GONE);
            uploadPictureText.setVisibility(View.GONE);
            uploadPictureText1.setVisibility(View.GONE);
        }
    }

    private void saveNameToSharedPreferences(String name) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    private void saveImageUriToSharedPreferences(Uri imageUri) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("imageUri", imageUri.toString());
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        // Exit the app when the back button is pressed
        finishAffinity();
    }
}
