package com.example.daniel.fittracker.activity;

import android.arch.persistence.room.Database;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.daniel.fittracker.DataAccesObjects.UserDAO;
import com.example.daniel.fittracker.DataObjects.User;
import com.example.daniel.fittracker.Database.AppDatabase;



public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    public EditText usernameEditText;
    private AppDatabase database;
    private User user;
    public EditText passwordEditText;
    public EditText emailEditText;
    public Button cancelButton;
    public Button registrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        usernameEditText = (EditText) findViewById(R.id.editTextRegistrationUserName);
        usernameEditText.setOnFocusChangeListener(this);
        passwordEditText = (EditText) findViewById(R.id.editTextRegistrationPassword);
        passwordEditText.setOnFocusChangeListener(this);
        emailEditText = (EditText) findViewById(R.id.editTextRegistrationEmail);
        emailEditText.setOnFocusChangeListener(this);
        registrationButton = (Button)findViewById(R.id.btnregistration);
        registrationButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.btncancelRegistration);
        cancelButton.setOnClickListener(this);

        database = AppDatabase.getDatabase(getApplicationContext());
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (view == usernameEditText) {
                String text = usernameEditText.getText().toString();
                if (text.equals("Name")) {
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
                    usernameEditText.setText("Name");
                }
            } else if (view != passwordEditText) {
                String text = passwordEditText.getText().toString();
                if (text.equals("Password") || text.equals(null)) {
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    passwordEditText.setText("Password");
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view == registrationButton) {

            try {
                user = database.userDAO().checkIfUserExists(usernameEditText.getText().toString(), emailEditText.getText().toString());
                if(user == null) {
                    user.setEmail(emailEditText.getText().toString());
                    user.setUsername(usernameEditText.getText().toString());
                    user.setPassword(passwordEditText.getText().toString());
                    database.userDAO().addUser(user);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage(R.string.username_exists)
                            .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                }catch(Exception e){
                System.out.println(e.getStackTrace());
            }

            Intent intentdashboard = new Intent(this, LoginActivity.class);
            startActivity(intentdashboard);
        } else if (view == cancelButton) {
            Intent intenteregistration = new Intent(this, LoginActivity.class);
            startActivity(intenteregistration);
        }
    }
}
