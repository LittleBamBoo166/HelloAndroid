package com.example.firebase_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "ERROR" ;
    private FirebaseAuth mAuth;
    TextView goToLoginTv;
    EditText userEmailEt, userPasswordEt, userPasswordConfirmEt;
    MaterialButton signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        goToLoginTv = findViewById(R.id.go_to_login);
        goToLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        userEmailEt = findViewById(R.id.register_name_edit_text);
        userPasswordEt = findViewById(R.id.register_password_edit_text);
        userPasswordConfirmEt = findViewById(R.id.register_password_confirm_edit_text);
        signUpBtn = findViewById(R.id.register_sign_up_button);

        mAuth = FirebaseAuth.getInstance();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = userEmailEt.getText().toString().trim();
        String password = userPasswordEt.getText().toString().trim();
        String passwordConfirm = userPasswordConfirmEt.getText().toString().trim();

        if (email.isEmpty()) {
            userEmailEt.setError("Email is required");
            userEmailEt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            userPasswordEt.setError("Password is required");
            userPasswordEt.requestFocus();
            return;
        }

        if (passwordConfirm.isEmpty()) {
            userPasswordConfirmEt.setError("Confirm your password");
            userPasswordConfirmEt.requestFocus();
            return;
        }

        if (password.length() < 6) {
            userPasswordEt.setError("Min password length should be 6 characters");
        }

        if (!passwordConfirm.equals(password)) {
            userPasswordConfirmEt.setError("Not match");
            userPasswordConfirmEt.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmailEt.setError("Please provide a valid email");
            userEmailEt.requestFocus();
            return;
        }

//        Toast.makeText(RegisterActivity.this, "Register!", Toast.LENGTH_LONG).show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(email);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Register successfully!", Toast.LENGTH_LONG).show();
                                        Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(mainActivity);
//                                       Log.e("error",t)
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Register failed!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(RegisterActivity.this, "Register failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}