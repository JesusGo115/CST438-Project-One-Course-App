package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.CourseAppDAO;

public class AddCourseActivity extends AppCompatActivity {

    public static AddCourseActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        Log.d("AddCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button submit_button = findViewById(R.id.submit);
        submit_button.setOnClickListener(v -> checkInputs());
    }

    public void alert(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setPositiveButton("Okay", (dialogInterface, i) -> {
            if(title.equals("Success!")) {
                finish();
            } else {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setMessage(message);
        dialog.show();
    }

    public void checkInputs() {
        EditText course_name = findViewById(R.id.course_name);
        EditText course_id = findViewById(R.id.course_id);
        EditText instructor_name = findViewById(R.id.instructor_name);
        EditText start_date = findViewById(R.id.start_date);
        EditText end_date = findViewById(R.id.end_date);

        CourseAppDAO dao = AppDatabase.getAppDatabase(AddCourseActivity.this).getCourseDao();

        String course_name_ = course_name.getText().toString();
        String course_id_ = course_id.getText().toString();
        String instructor_ = instructor_name.getText().toString();
        String start_ = start_date.getText().toString();
        String end_ = end_date.getText().toString();

        if(course_name_.isEmpty()) {
            alert("Error", "No Course Name entered");
            return;
        }

        if(course_id_.isEmpty()) {
            alert("Error","No Course ID entered");
            return;
        }

        if(instructor_.isEmpty()) {
            alert("Error", "No Instructor Name entered");
            return;
        }

        if(start_.isEmpty()) {
            alert("Error","No Start Date entered");
            return;
        }

        if(end_.isEmpty()) {
            alert("Error","No End Date entered");
            return;
        }

        Course course = new Course(course_name_, course_id_, instructor_, start_, end_, MainActivity.username);
        dao.addCourse(course);

        Log.d("AddCourseActivity", "course added to user");
        alert("Success!", String.format("Course added: %s %s", course.getTitle(), course.getDescription()));
    }
}