package com.example.tourism_guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tourism_guide.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        db.execSQL("CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT, role TEXT)");

        // Create Listings Table (for activities and accommodations)
        db.execSQL("CREATE TABLE Listings (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, location TEXT, name TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Listings");
        onCreate(db);
    }

    // Insert New User
    public boolean insertUser(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("role", role);

        long result = db.insert("Users", null, values);
        return result != -1;
    }

    // Authenticate User (Login)
    public String authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT role FROM Users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }
        cursor.close();
        return null;
    }

    // Insert New Listing (Activity or Accommodation)
    public boolean insertListing(String type, String location, String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("location", location);
        values.put("name", name);
        values.put("description", description);

        long result = db.insert("Listings", null, values);
        return result != -1;
    }

    // Retrieve Listings by Location
    public Cursor getListingsByLocation(String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Listings WHERE location=?", new String[]{location});
    }
}
