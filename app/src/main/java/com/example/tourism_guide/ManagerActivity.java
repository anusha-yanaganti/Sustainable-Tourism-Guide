package com.example.tourism_guide;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {

    private EditText etLocation, etName, etDescription;
    private RadioGroup rgType;
    private Button btnAddListing;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        // Initialize UI elements
        etLocation = findViewById(R.id.etLocation);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        rgType = findViewById(R.id.rgType);
        btnAddListing = findViewById(R.id.btnAddListing);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Button click listener to add a listing
        btnAddListing.setOnClickListener(v -> addListing());
    }

    private void addListing() {
        String location = etLocation.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int selectedTypeId = rgType.getCheckedRadioButtonId();

        if (location.isEmpty() || name.isEmpty() || description.isEmpty() || selectedTypeId == -1) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected type (Activity or Accommodation)
        String type = ((RadioButton) findViewById(selectedTypeId)).getText().toString();

        // Insert into database
        boolean success = dbHelper.insertListing(type, location, name, description);

        if (success) {
            Toast.makeText(this, "Listing Added Successfully", Toast.LENGTH_SHORT).show();
            etLocation.setText("");
            etName.setText("");
            etDescription.setText("");
            rgType.clearCheck();
        } else {
            Toast.makeText(this, "Failed to Add Listing", Toast.LENGTH_SHORT).show();
        }
    }
}
