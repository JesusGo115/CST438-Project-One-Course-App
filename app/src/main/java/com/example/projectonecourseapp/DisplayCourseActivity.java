package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.Assignment;
import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.CourseAppDAO;

import java.util.ArrayList;
import java.util.List;

public class DisplayCourseActivity extends AppCompatActivity {

    public static DisplayCourseActivity instance = null;

    Course course = null;
    List<Assignment> assignments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        Log.d("DisplayCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String course_title = getIntent().getStringExtra("course");
        DisplayUserCourseActivity.username = getIntent().getStringExtra("username");
        assert course_title != null;

        TextView message = findViewById(R.id.message);
        message.setText("");

        Button add_assignment = findViewById(R.id.add_assignment_button);
        add_assignment.setOnClickListener(view -> {
            Intent intent = new Intent(DisplayCourseActivity.this, AddAssignmentActivity.class);
            intent.putExtra("course", course_title);
            startActivity(intent);
            finish();
        });

        Button delete_assignment = findViewById(R.id.delete_assignment_button);
        delete_assignment.setOnClickListener(view -> {

        });

        CourseAppDAO dao = AppDatabase.getAppDatabase(DisplayCourseActivity.this).getCourseDao();
        course = dao.getCourseByTitle(course_title);
        message.setText(String.format("%s %s\n", course.getTitle(), course.getDescription()));

        // get assignments
        assignments = dao.getAssignmentByCourseId(course_title);
        if(assignments.isEmpty()) {
            message.append("No assignments added yet");
        } else {
            updateList();
        }
    }

    public void updateList() {
        ListView lv = findViewById(R.id.list_view);
        List<String> rows = new ArrayList<>();
        for(Assignment assignment: assignments) {
            // temporary display
            rows.add(String.format("%s %s %s %s/%s", assignment.getDetails(),
                    assignment.getAssignedDate(), assignment.getDueDate(), assignment.getEarnedScore(), assignment.getMaxScore()));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, rows);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener((parent, view, i, l) -> {
            // not sure we need this
        });
    }
}