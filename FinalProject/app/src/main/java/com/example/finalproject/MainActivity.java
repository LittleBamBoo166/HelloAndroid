package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText userNameEt, passwordEt;
    Button loginBtn;
    TextView goToSignupTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEt = findViewById(R.id.editTextPersonNameLogin);
        passwordEt = findViewById(R.id.editTextPasswordLogin);
        loginBtn = findViewById(R.id.buttonLogin);
        goToSignupTv = findViewById(R.id.textViewRegister);


        goToSignupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(signUpActivity);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String email = userNameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (email.isEmpty()) {
            userNameEt.setError("Email is required");
            userNameEt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userNameEt.setError("Please enter a valid email");
            userNameEt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEt.setError("Password is required");
            passwordEt.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEt.setError("Min password length is 6 characters");
            passwordEt.requestFocus();
            return;
        }

        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, ListActivity.class));
    }
}