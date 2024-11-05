package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMessages = findViewById(R.id.btnMessages);
        Button btnCallLogs = findViewById(R.id.btnCallLogs);
        Button btnContacts = findViewById(R.id.btnContacts);

        btnMessages.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
            startActivity(intent);
        });

        btnCallLogs.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CallLogsActivity.class);
            startActivity(intent);
        });

        btnContacts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
            startActivity(intent);
        });
    }
}