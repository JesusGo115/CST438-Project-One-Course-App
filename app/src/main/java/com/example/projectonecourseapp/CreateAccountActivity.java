package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.CourseAppDAO;
import com.example.projectonecourseapp.db.User;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CreateAccountActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button create_account_button = findViewById(R.id.create_account_button);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    public void createAccount() {
        TextView message = findViewById(R.id.message);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String username_ = username.getText().toString();
        String password_ = password.getText().toString();

        // make requirement check later

        // check if user exists
        User user = AppDatabase.getAppDatabase(CreateAccountActivity.this).
                getCourseDao().getUserByUsername(username_);
        if(user == null) {
            // add new user
            User new_user = new User(username_, password_);
            CourseAppDAO dao = AppDatabase.getAppDatabase(CreateAccountActivity.this).getCourseDao();
            dao.addUser(new_user);
            alert("Success!", "Account successfully created.");
        } else {
            // username already exists
            message.setText(R.string.username_taken);
        }
    }

    public void alert(String title, final String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(message.equals("Account successfully created.")) {
                    finish();
                } else {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }
}