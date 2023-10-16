package com.farmwiseai.myapplicationqna;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends Activity {

    private static final int SELECT_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_activity);

        // Retrieve the name and image URI from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedName = preferences.getString("name", "Default Name");
        String savedImageUri = preferences.getString("imageUri", null);

        // Find your views
        EditText nameEditText = findViewById(R.id.text_area);
        ImageView profileImageView = findViewById(R.id.selected_image_view);

        // Set the saved name in the EditText
        nameEditText.setText(savedName);

        // Check if there's a saved image URI
        if (savedImageUri != null) {
            Uri imageUri = Uri.parse(savedImageUri);
            // Set the saved image in the ImageView
            profileImageView.setImageURI(imageUri);
        }

        TextView uploadYourText = findViewById(R.id.text_view_1);
        TextView profileText = findViewById(R.id.text_view_2);
        ImageButton centerImageButton = findViewById(R.id.center_image_button);

        // Make the "Upload your" and "Profile" text invisible
        uploadYourText.setVisibility(View.INVISIBLE);
        profileText.setVisibility(View.INVISIBLE);

        // Set an OnClickListener for the ImageButton to open the gallery
        centerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Find your other views and set their listeners

        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedGender = parentView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected Gender: " + selectedGender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here, but you can handle it if needed.
            }
        });

        Button saveButton = findViewById(R.id.SavedButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Save button click here
                // For example, show a toast message
                Toast.makeText(EditProfile.this, "Profile Saved!", Toast.LENGTH_SHORT).show();

                // Navigate to ProfileSavedActivity
                Intent intent = new Intent(EditProfile.this, Screen1.class);
                startActivity(intent);
            }
        });
    }

    // Method to open the gallery
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            // The user has selected an image from the gallery
            Uri imageUri = data.getData();

            // Set the selected image in the ImageView
            ImageView profileImageView = findViewById(R.id.selected_image_view);
            profileImageView.setImageURI(imageUri);
        }
    }
}

