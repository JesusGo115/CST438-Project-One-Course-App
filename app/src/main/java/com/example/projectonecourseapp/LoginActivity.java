package com.example.projectonecourseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectonecourseapp.db.AppDatabase;
import com.example.projectonecourseapp.db.CourseAppDAO;
import com.example.projectonecourseapp.db.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_ = username.getText().toString();
                String password_ = password.getText().toString();
                TextView message = findViewById(R.id.message);

                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    // error empty, implement
                    message.setText(R.string.missing_user_pass);
                    return;
                }

                CourseAppDAO dao = AppDatabase.getAppDatabase(LoginActivity.this).dao();
                User user = dao.login(username_, password_);
                if(user == null) {
                    // unsuccessful login, check is username or password is wrong
                    User user_temp = dao.getUserByUsername(username_);
                    if(user_temp == null) {
                        // username error
                        message.setText(R.string.incorrect_username);
                    } else {
                        // password error
                        message.setText(R.string.incorrect_password);
                    }
                } else {
                    // successful
                    MainActivity.username = username_;
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("Success!");
                    alert.setMessage("Welcome " + username_);
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alert.show();
                }
            }
        });
    }
}