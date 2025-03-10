package com.example.tourism_guide;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TouristActivity extends AppCompatActivity {

    private EditText etLocation;
    private Button btnSearch;
    private ListView listViewListings;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist);

        // Initialize UI elements
        etLocation = findViewById(R.id.etLocation);
        btnSearch = findViewById(R.id.btnSearch);
        listViewListings = findViewById(R.id.listViewListings);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Search button click listener
        btnSearch.setOnClickListener(v -> searchListings());
    }

    private void searchListings() {
        String location = etLocation.getText().toString().trim();

        if (location.isEmpty()) {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve listings from the database
        Cursor cursor = dbHelper.getListingsByLocation(location);
        ArrayList<String> listings = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                listings.add(type + ": " + name + "\nDescription: " + description);
            } while (cursor.moveToNext());
        } else {
            listings.add("No listings found for " + location);
        }

        cursor.close();

        // Display results in ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listings);
        listViewListings.setAdapter(adapter);
    }
}
