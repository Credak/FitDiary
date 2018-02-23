package com.example.daniel.fittracker.activity;

import android.arch.persistence.room.Database;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniel.fittracker.DataObjects.User;
import com.example.daniel.fittracker.Database.AppDatabase;

import java.util.List;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private static final String TAG = RegistrationActivity.class.getName();
    private AppDatabase database;
    public User user;
    public EditText usernameEditText;
    public EditText passwordEditText;
    public EditText emailEditText;
    public Button cancelButton;
    public Button registrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        usernameEditText = (EditText) findViewById(R.id.editText_Username);
        usernameEditText.setOnFocusChangeListener(this);
        passwordEditText = (EditText) findViewById(R.id.editText_Password);
        passwordEditText.setOnFocusChangeListener(this);
        emailEditText = (EditText) findViewById(R.id.editText_registrationEmail);
        emailEditText.setOnFocusChangeListener(this);

        registrationButton = (Button)findViewById(R.id.btn_registration);
        registrationButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.btn_cancelregistration);
        cancelButton.setOnClickListener(this);

        database = AppDatabase.getDatabase(getApplicationContext());
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (view == usernameEditText) {
                String text = usernameEditText.getText().toString();
                if (text.equals("Username")) {
                    usernameEditText.setText("");
                }
            } else if (view == passwordEditText) {
                String text = passwordEditText.getText().toString();
                if (text.equals("Password")) {
                    passwordEditText.setText("");
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                }
            }else if(view == emailEditText){
                String text =  emailEditText.getText().toString();
                if(text.equals("Email")){
                    emailEditText.setText("");
                    emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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
                if (text.equals("") || text.equals(null)) {
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    passwordEditText.setText(R.string.password);
                }
            }else if(view != emailEditText){
                String text = emailEditText.getText().toString();
                if(text.equals("") || text.equals(null)){
                    emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    emailEditText.setText(R.string.email);
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view == registrationButton) {
            try {
                user = new User();
                if(checkIfUserNotExists(database, user)){
                    addUser(database, user);
                    Log.d(RegistrationActivity.TAG, "Created user with Username: " + user.getUsername());
                }else{
                    Log.d(RegistrationActivity.TAG, "A user with this Email / Username already exists");
                }
                }catch(Exception e){
                e.printStackTrace();
            }

            Intent intentdashboard = new Intent(this, LoginActivity.class);
            startActivity(intentdashboard);
        } else if (view == cancelButton) {
            Intent intenteregistration = new Intent(this, LoginActivity.class);
            startActivity(intenteregistration);
        }
    }

    private User addUser(final AppDatabase db, User user){
        user.setPassword(passwordEditText.getText().toString());
        user.setUsername(usernameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        db.userDAO().insertAll(user);
        return user;
    }
    private boolean checkIfUserNotExists(final AppDatabase db, User user){
        user.setUsername(usernameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user = db.userDAO().checkIfUserNotExists(user.getUsername(), user.getEmail());
        if (user == null) {
            return true;
        }
        return false;
    }
}
