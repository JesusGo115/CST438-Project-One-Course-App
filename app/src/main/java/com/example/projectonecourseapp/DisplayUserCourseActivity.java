package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayUserCourseActivity extends AppCompatActivity {

    public static DisplayUserCourseActivity instance = null;

    // list of courses?
    List<Course> courses_taken = new ArrayList<>();
    Course course = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DisplayUserCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_course);

        TextView message = findViewById(R.id.message);
        message.setText(String.format("Welcome, %s", MainActivity.username));

        Button add_course_button = findViewById(R.id.add_course_button);
        add_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayUserCourseActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

        // display courses...



    }
}