package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.projectonecourseapp.db.AppDatabase;

public class MainActivity extends AppCompatActivity {

    public static String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MainActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar  = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // load database
        AppDatabase.getAppDatabase(MainActivity.this).loadData(this);

        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick for login called");

                Intent intent = getIntent(MainActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        Button create_account_button = findViewById(R.id.create_account_button);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick for create account called");

                Intent intent = getIntent(MainActivity.this, CreateAccountActivity.class);

                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context, Class<?> classActivity) {
        return new Intent(context, classActivity);
    }
}
