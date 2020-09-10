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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        Log.d("DisplayUserCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_course);

        CourseAppDAO dao = AppDatabase.getAppDatabase(DisplayUserCourseActivity.this).getCourseDao();
        User user = dao.getUserByUsername(MainActivity.username);

        TextView message = findViewById(R.id.message);
        message.setText(String.format("Welcome, %s", user.getUserName()));

        Button add_course_button = findViewById(R.id.add_course_button);
        add_course_button.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayUserCourseActivity.this, AddCourseActivity.class);
            DisplayUserCourseActivity.this.startActivity(intent);
        });

        // load the course data
        List<Course> courses = dao.getCoursesTaken(MainActivity.username);

        if(courses.isEmpty()) {
            // display something in view to notify user no courses taking.
            Log.d("DisplayUserCourseActivity", "no courses this username is taking");
        } else {
            Log.d("DisplayCourseActivity", String.format("User is taking %d courses", courses.size()));

            // Display courses list view?
            /*
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                    (this, android.R.layout.simple_list_item_1, new ArrayList<>());
            ListView lv = findViewById(R.id.list_view);
            lv.setAdapter(arrayAdapter);

            lv.setOnItemClickListener((parent, view, position, id) -> {
                String selectedItem = (String) parent.getItemAtPosition(position);

            });*/
        }
    }
}