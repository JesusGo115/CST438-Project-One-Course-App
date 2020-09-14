package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.Assignment;
import com.example.projectonecourseapp.db.Course;
import com.example.projectonecourseapp.db.CourseAppDAO;

import java.util.List;


public class DeleteCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> checkInputs());

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

    private void checkInputs() {

        CourseAppDAO mProjectDao = AppDatabase.getAppDatabase(DeleteCourseActivity.this).getCourseDao();

        EditText courseNameEditText = findViewById(R.id.course_name_editText);
        EditText courseIdEditText = findViewById(R.id.course_id_editText);

        String courseNameStr = courseNameEditText.getText().toString();
        String courseIdStr = courseIdEditText.getText().toString();

        if(courseNameStr.isEmpty()) {
            alert("Error", "No Course Name entered");
            return;
        }

        if(courseIdStr.isEmpty()) {
            alert("Error","No Course ID entered");
            return;
        }

        Course tempCourse = mProjectDao.getCourseByCourseIdAndCourseName(courseIdStr, courseNameStr);

        if (tempCourse != null) {

            deleteCourseAssignments(mProjectDao, tempCourse);

            mProjectDao.deleteCourse(tempCourse);

            Log.d("DeleteCourseActivity", "course deleted");
            alert("Success!", String.format("Course deleted: %s %s", tempCourse.getCourseId(), tempCourse.getDescription()));

        }
        else {
            alert("Error","Course does not exist");
        }

    }

    private void deleteCourseAssignments(CourseAppDAO mDao, Course course) {

        int assignmentCounter = mDao.countAssignmentsByCourseId(course.getCourseId());

        if (assignmentCounter > 0) {
            List<Assignment> assignmentList = mDao.getAllAssignmentsByCourseId(course.getCourseId());

            for (Assignment eachAssignment : assignmentList) {
                mDao.deleteAssignment(eachAssignment);
            }
        }

    }

}