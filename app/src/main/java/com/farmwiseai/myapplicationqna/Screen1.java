package com.farmwiseai.myapplicationqna;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Screen1 extends AppCompatActivity {
    private EditText textArea;
    private ImageButton sendButton;
    private RelativeLayout messageContainer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1_activity);
        textArea = findViewById(R.id.text_area);
        sendButton = findViewById(R.id.sendButton);
        messageContainer = findViewById(R.id.messageContainer);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the message text from the EditText
                String messageText = textArea.getText().toString();

                if (!messageText.isEmpty()) {
                    // Create a new message view (e.g., a TextView) and add it to the right side
                    createMessageView(messageText);

                    // Clear the message input area
                    textArea.setText("");

                    // Change the send button background to its initial state
                    sendButton.setBackgroundResource(R.drawable.button_background);

                    // Show the chat_bubble_right after sending a message
                    messageContainer.setVisibility(View.VISIBLE);
                }
            }
        });

        // Listen to text changes in the EditText to change the send button background
        textArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    sendButton.setBackgroundResource(R.drawable.sendsymbolimg);
                } else {
                    sendButton.setBackgroundResource(R.drawable.send_button_active);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ImageButton addChatImage = findViewById(R.id.add_chatimg);
        addChatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to open the image gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1); // You can use any requestCode you like
            }
        });

        ImageButton moreButton = findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a PopupMenu
                PopupMenu popup = new PopupMenu(Screen1.this, view);

                // Inflate the menu from XML
                getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                // Set a click listener for the menu items
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.history) {
                            // Handle the "History" item click
                            // Add your logic here
                            return true;
                        } else if (itemId == R.id.profile) {
                            Intent profileIntent = new Intent(Screen1.this, EditProfile.class);
                            startActivity(profileIntent);
                            return true;
                        } else if (itemId == R.id.deletechat) {
                            Intent deleteChatIntent = new Intent(Screen1.this, DeleteChat.class);
                            startActivity(deleteChatIntent);
                            return true;
                        }
                        return false;
                    }
                });

                // Show the popup menu
                popup.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // The user has selected an image from the gallery
            Uri selectedImageUri = data.getData();

            // You can do further processing with the selected image, such as displaying it or uploading it.
        }
    }
    @Override
    public void onBackPressed() {
        // Exit the app when the back button is pressed
        finishAffinity();
    }

    private void createMessageView(String message) {
        // Create a new view for the message (e.g., a TextView)
        TextView messageView = new TextView(this);
        messageView.setText(message);
        messageView.setTextColor(getResources().getColor(R.color.black));

        // Add it to the right side message container
        messageContainer.addView(messageView);

        // Show the chat_bubble_right after sending a message
        messageContainer.setVisibility(View.VISIBLE);
    }
}
