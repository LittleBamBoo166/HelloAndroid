package com.example.firebase_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class AddStudentActivity extends AppCompatActivity {
    EditText nameEt, idEt;
    MaterialButton addBtn;
    DAOStudent daoStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEt = findViewById(R.id.add_student_name);
        idEt = findViewById(R.id.add_student_id);
        addBtn = findViewById(R.id.add_student_materialBtn);

        daoStudent = new DAOStudent();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String name = nameEt.getText().toString().trim();
        String id = idEt.getText().toString().trim();

        if (name.isEmpty()) {
            nameEt.setError("Name is required");
            nameEt.requestFocus();
            return;
        }

        if (id.isEmpty()) {
            idEt.setError("Id is required");
            idEt.requestFocus();
            return;
        }

        Student student = new Student(name, id);
        daoStudent.add(student).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddStudentActivity.this, "Add student successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddStudentActivity.this, "Add student failed", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", task.getException().getMessage());
                }
            }
        });
    }
}