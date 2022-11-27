package com.example.firebase_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    ImageButton addStudentBtn;
    ListView studentLv;
    ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentArrayList = new ArrayList<>();
        studentLv = findViewById(R.id.student_list_view);
        addStudentBtn = findViewById(R.id.add_student_button);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    String key = snapshot.getKey();
                    student.setId(key);
                    studentArrayList.add(student);
                }
                StudentAdapter adapter = new StudentAdapter(StudentListActivity.this, 0, studentArrayList);
                Log.d("MESSAGE", "" + studentArrayList.size());
                studentLv.setAdapter(adapter);
                studentLv.setClickable(true);

                addStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(StudentListActivity.this, AddStudentActivity.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentListActivity.this, "Failed to show the student list", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", databaseError.getMessage());
            }
        });
    }
}