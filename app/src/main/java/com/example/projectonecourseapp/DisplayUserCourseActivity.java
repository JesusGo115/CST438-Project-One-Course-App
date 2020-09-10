package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.CourseAppDAO;
import com.example.projectonecourseapp.db.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayUserCourseActivity extends AppCompatActivity {

    public static DisplayUserCourseActivity instance = null;

    List<Course> courses = new ArrayList<>();
    Course course = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        Log.d("DisplayUserCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_course);

        CourseAppDAO dao = AppDatabase.getAppDatabase(DisplayUserCourseActivity.this).getCourseDao();
        User user = dao.getUserByUsername(MainActivity.username);

        TextView message = findViewById(R.id.message);
        message.setText("");

        if(user.getFirstName() != null) {
            message.setText(String.format("Welcome, %s\n", user.getFirstName()));
        } else {
            message.setText(String.format("Welcome, %s\n", user.getUserName()));
        }

        Button add_course_button = findViewById(R.id.add_course_button);
        add_course_button.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayUserCourseActivity.this, AddCourseActivity.class);
            startActivity(intent);
            finish();
        });

        Button edit_user_button = findViewById(R.id.edit_user);
        edit_user_button.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayUserCourseActivity.this, EditUserActivity.class);
            startActivity(intent);
            finish();
        });

        // load the course data
        courses = dao.getCoursesTaken(MainActivity.username);

        if(courses.isEmpty()) {
            Log.d("DisplayUserCourseActivity", "no courses this username is taking");
            message.append("No Courses added yet");
        } else {
            Log.d("DisplayUserCourseActivity", String.valueOf(courses.size()));
            updateList();
        }
    }

    // update course list
    public void updateList() {
        ListView lv = findViewById(R.id.list_view);
        List<String> rows = new ArrayList<>();
        for(Course course: courses) {
            // switch this up...
            rows.add(String.format("Course Name: %s\nCourse ID: %s\nInstructor: %s" +
                    "\nStart Date: %s\nEnd Date: %s", course.getDescription(), course.getTitle(),
                    course.getInstructor(), course.getStartDate(), course.getEndDate()));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (instance, android.R.layout.simple_list_item_1, rows);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener((parent, view, i, l) -> {
            // get item from ListView
            String selected_item = (String) parent.getItemAtPosition(i);
            // get course
            Log.d("DisplayUserActivity", selected_item);





        });


    }


}