package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.Assignment;
import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.CourseAppDAO;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    public static CourseActivity instance = null;

    Course course = null;
    List<Assignment> assignments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView message = findViewById(R.id.message);
        String course_title = getIntent().getStringExtra("course");

        CourseAppDAO dao = AppDatabase.getAppDatabase(CourseActivity.this).getCourseDao();

        assert course_title != null;
        Log.d("CourseActivity", course_title);

        course = dao.getCourseByTitle(course_title);
        message.setText(String.format("%s %s", course.getTitle(), course.getDescription()));

    }
}