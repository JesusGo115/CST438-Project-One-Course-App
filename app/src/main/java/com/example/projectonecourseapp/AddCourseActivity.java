package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddCourseActivity", "onCreate called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Button submit_button = findViewById(R.id.submit);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
    }

    public void alert(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setMessage(error);
        dialog.show();
    }

    public void checkInputs() {
        EditText course_name = findViewById(R.id.course_name);
        EditText course_id = findViewById(R.id.course_id);
        EditText instructor_name = findViewById(R.id.instructor_name);
        EditText start_date = findViewById(R.id.start_date);
        EditText end_date = findViewById(R.id.end_date);

        if(course_name.getText().toString().isEmpty()) {
            alert("No Course Name entered");
        } else if(course_id.getText().toString().isEmpty()) {
            alert("No Course ID entered");
        } else if(instructor_name.getText().toString().isEmpty()) {
            alert("No Instructor Name entered");
        } else if(start_date.getText().toString().isEmpty()) {
            alert("No Start Date entered");
        } else if(end_date.getText().toString().isEmpty()) {
            alert("No End Date entered");
        }

        // add course to user list


    }
}