package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText usernameEditText = (EditText) findViewById(R.id.username);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        EditText passwordConfirmEditText = (EditText) findViewById(R.id.passwordconfirm);

        MaterialButton signupBtn = (MaterialButton) findViewById(R.id.signupbtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordConfirm = passwordConfirmEditText.getText().toString();
                if (passwordConfirm.equals(password)) {
                    signup(userName, password);
                } else {
                    Toast.makeText(SignUpActivity.this, "CONFIRM YOUR PASSWORD AGAIN", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView loginText = (TextView) findViewById(R.id.movetologin);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginView = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(loginView);
            }
        });
    }

    private void signup(String name, String password) {
        try {
            RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
            String url = "http://10.0.3.2:3000/singers";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("password", password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String id = response.getString("id");
                        MainActivity.USER_ID = id;
                        Toast.makeText(SignUpActivity.this, "SINGUP SUCCESSFULL", Toast.LENGTH_SHORT).show();
                        Intent loginView = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(loginView);
                    } catch (JSONException e) {
                        Toast.makeText(SignUpActivity.this, "SIGNUP FAILED", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUpActivity.this, "SIGNUP FAILED", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}