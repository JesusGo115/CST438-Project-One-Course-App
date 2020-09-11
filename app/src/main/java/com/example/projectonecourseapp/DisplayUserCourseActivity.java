package com.example.projectonecourseapp;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.projectonecourseapp.db.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayUserCourseActivity extends AppCompatActivity {

    public static DisplayUserCourseActivity instance = null;
    public static String username = null;

    List<Course> courses = new ArrayList<>();
    List<Assignment> assignment_grades = new ArrayList<>();
    Course course = null;
    Integer course_grade = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;

        Log.d("DisplayUserCourseActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_course);

        CourseAppDAO dao = AppDatabase.getAppDatabase(DisplayUserCourseActivity.this).getCourseDao();
        username = MainActivity.username;
        User user = dao.getUserByUsername(username);

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

        Button delete_course_button = findViewById(R.id.delette_course_button);
        delete_course_button.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayUserCourseActivity.this, DeleteCourseActivity.class);
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
            message.append("No Courses added yet");
        } else {
            updateList();
        }
    }

    // update course list
    public void updateList() {
        ListView lv = findViewById(R.id.list_view);
        List<String> rows = new ArrayList<>();
        CourseAppDAO dao = AppDatabase.getAppDatabase(this).getCourseDao();
        String grade_temp;
        for(Course course: courses) {
            // get overall assignment grades from course
            assignment_grades = dao.getAssignmentByCourseId(course.getTitle());
            Log.d("DisplayUserActivity", "# of assignments : " + assignment_grades.size());

            if(course_grade == null) {
                grade_temp = "N/A";
            } else {
                grade_temp = String.valueOf(course_grade);
                grade_temp += "%";
            }
            rows.add(String.format("Course: %s %s\nInstructor: %s" +
                    "\nStart Date: %s\nEnd Date: %s\nOverall Score: %s", course.getTitle(), course.getDescription(),
                    course.getInstructor(), course.getStartDate(), course.getEndDate(), grade_temp));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (instance, android.R.layout.simple_list_item_1, rows);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener((parent, view, i, l) -> {
            // get item from ListView
            String selected_item = (String) parent.getItemAtPosition(i);
            // get course
            String course_title = selected_item.split(" ")[1];
            course = dao.getCourseByTitle(course_title);

            if(course != null) {
                Intent intent = new Intent(DisplayUserCourseActivity.this, DisplayCourseActivity.class);
                intent.putExtra("course", course.getTitle());
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });
    }

//    public Integer getAverage(List<Assignment> assignments) {
//        Integer grades;
//
//        return grade;
//    }
}