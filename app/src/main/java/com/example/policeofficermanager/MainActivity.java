package com.example.policeofficermanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton loginBtn;
    EditText userNameEt, userPasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.login_button);
        userNameEt = findViewById(R.id.user_name_edit_text);
        userPasswordEt = findViewById(R.id.password_edit_text);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEt.getText().toString().trim();
                String userPassword = userPasswordEt.getText().toString().trim();

                if (userName.isEmpty()) {
                    userNameEt.setError("User name is required");
                    userNameEt.requestFocus();
                    return;
                }

                if (userPassword.isEmpty()) {
                    userPasswordEt.setError("User password is required");
                    userPasswordEt.requestFocus();
                    return;
                }

                startActivity(new Intent(MainActivity.this, PoliceListActivity.class));
            }
        });
    }
}