package com.example.androidbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PasswordActivity extends AppCompatActivity {
    TextView greetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        greetings = findViewById(R.id.tv_greetings);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("NAME");

            // Display the name in a TextView

            greetings.setText("Hello, " + name + "!");

        }
    }
}