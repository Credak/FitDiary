package com.example.daniel.fittracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniel.fittracker.DataObjects.User;
import com.example.daniel.fittracker.Database.AppDatabase;
import com.example.daniel.fittracker.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private User user;
    private AppDatabase database;
    public EditText usernameEditText;
    public EditText passwordEditText;
    public Button loginbutton;
    public Button registrationbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.editText_Username);
        usernameEditText.setOnFocusChangeListener(this);
        passwordEditText = (EditText) findViewById(R.id.editText_Password);
        passwordEditText.setOnFocusChangeListener(this);
        loginbutton = (Button) findViewById(R.id.btn_login);
        loginbutton.setOnClickListener(this);
        registrationbutton = (Button) findViewById(R.id.btn_register);
        registrationbutton.setOnClickListener(this);

        database = AppDatabase.getDatabase(getApplicationContext());
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (view == usernameEditText) {
                String text = usernameEditText.getText().toString();
                if (text.equals("Username")) {
                    usernameEditText.setText(" ");
                }
            } else if (view == passwordEditText) {
                String text = passwordEditText.getText().toString();
                if (text.equals("Password")) {
                    passwordEditText.setText("");
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                }
            }
        }
        if (!hasFocus) {
                if (view != usernameEditText) {
                    String text = usernameEditText.getText().toString();
                    if (text.equals("") || text.equals(null)) {
                        usernameEditText.setText(R.string.username);
                    }
                } else if (view != passwordEditText) {
                    String text = passwordEditText.getText().toString();
                    if (text.equals("Password") || text.equals(null)) {
                        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                        passwordEditText.setText(R.string.password);
                    }
                }
            }

    }

    @Override
    public void onClick(View view) {
        if (view == loginbutton) {
            user = new User();
            if(checkLoginUser(database, user) == null){
            }else {
                Intent dashboardintent = new Intent(this, DashboardActivity.class);
                startActivity(dashboardintent);
            }
        } else if (view == registrationbutton) {
            Intent intenteregistration = new Intent(this, RegistrationActivity.class);
            startActivity(intenteregistration);
        }
    }

    private User checkLoginUser(AppDatabase db, User user){
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user = db.userDAO().getLoginUser(user.getUsername(), user.getPassword());
        return user;
    }
}

