package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    Assignment assignment = null;
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
        delete_assignment.setOnClickListener(view -> message.setText(R.string.delete_assignment_instruction));

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
        CourseAppDAO dao = AppDatabase.getAppDatabase(this).getCourseDao();

        for(Assignment assignment: assignments) {
            // temporary display
            rows.add(String.format("%s\nAssigned: %s\nDue: %s \nScore: %s/%s\nCategory: %s", assignment.getDetails(),
                    assignment.getAssignedDate(), assignment.getDueDate(), assignment.getEarnedScore(), assignment.getMaxScore(), assignment.getCategoryId()));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, rows);
        lv.setAdapter(arrayAdapter);

        // users can edit assignment
        lv.setOnItemClickListener((parent, view, i, l) -> {
            // get item position
            String selected_item = (String) parent.getItemAtPosition(i);
            // get assignment
            String selected_temp = selected_item.split("\n")[0];
            editAssignment(selected_temp);
        });

        lv.setOnItemLongClickListener((parent, view, i, l) -> {
            // make some alert here.
            Log.d("DisplayCourseActivity", "onItemLongClick");
            deleteAssignment();
            return true;
        });
    }

    public void editAssignment(String assignment_details) {

        CourseAppDAO dao = AppDatabase.getAppDatabase(this).getCourseDao();
        assignment = dao.getAssignmentByDetails(assignment_details);
        if(assignment != null) {

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText description = new EditText(this);
            description.setHint("Description about assignment");
            layout.addView(description);

            final EditText max_score = new EditText(this);
            max_score.setHint("Enter max score");
            layout.addView(max_score);

            final EditText earned_score = new EditText(this);
            earned_score.setHint("Enter your earned score");
            layout.addView(earned_score);

            final EditText assigned_date = new EditText(this);
            assigned_date.setHint("Enter assigned date");
            layout.addView(assigned_date);

            final EditText due_date = new EditText(this);
            due_date.setHint("Enter due date");
            layout.addView(due_date);

            final EditText category_id = new EditText(this);
            category_id.setHint("HW/Quiz/Project/Exam");
            layout.addView(category_id);

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Edit Assignment")
                    .setPositiveButton("Okay", (dialog, which) -> {
                        String description_ = description.getText().toString();
                        String max_score_ = max_score.getText().toString();
                        String earned_score_ = earned_score.getText().toString();
                        String assigned_date_ = assigned_date.getText().toString();
                        String due_date_ = due_date.getText().toString();
                        String category_id_ = category_id.getText().toString();

                        if(description_.isEmpty() || max_score_.isEmpty() || earned_score_.isEmpty()
                                || assigned_date_.isEmpty() || due_date_.isEmpty() || category_id_.isEmpty()) {
                            alert("Error", "Please don't leave any fields blank.");
                        }

                        assignment.setDetails(description_);
                        assignment.setMaxScore(max_score_);
                        assignment.setEarnedScore(earned_score_);
                        assignment.setAssignedDate(assigned_date_);
                        assignment.setDueDate(due_date_);
                        assignment.setCategoryId(category_id_);
                        dao.updateAssignment(assignment);

                        alert("Success!", "You have updated your assignment.");
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> alert("Edit Assignment", "No changes were made."))
                    .setOnCancelListener(dialog -> Log.d("DisplayCourseActivity", "No changes made"))
                    .setView(layout);
            builder.show();

        } else {
            // if somehow Assignment is null, exit
            alert("Error!", "Assignment does not exist!");
            finish();
        }

    }

    public void deleteAssignment() {

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


}