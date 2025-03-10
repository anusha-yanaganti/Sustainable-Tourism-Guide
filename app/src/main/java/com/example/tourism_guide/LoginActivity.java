package com.example.tourism_guide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private TextView tvNewUser; // Text prompting new users
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvNewUser = findViewById(R.id.tvNewUser); // New user text

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Login button click listener
        btnLogin.setOnClickListener(v -> loginUser());

        // Register button click listener
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check user credentials
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT role FROM Users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            db.close();

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            // Redirect based on role
            if ("Tourist".equals(role)) {
                startActivity(new Intent(this, TouristActivity.class));
            } else {
                startActivity(new Intent(this, ManagerActivity.class));
            }
            finish(); // Close login activity after successful login
        } else {
            cursor.close();
            db.close();
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
