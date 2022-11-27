package com.example.firebase_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private ArrayList<Student> studentArrayList;


    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.studentArrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.student_item, null);
        }
        if (studentArrayList.size() > 0) {
            Student a = studentArrayList.get(position);

            TextView name = convertView.findViewById(R.id.student_name_lv);
            TextView studentId = convertView.findViewById(R.id.student_id_lv);
            TextView id = convertView.findViewById(R.id.id_lv);
            id.setText(a.id);
            name.setText(a.name);
            studentId.setText(a.studentId);

            ImageButton deleteBtn = convertView.findViewById(R.id.delete_student_button);
            ImageButton updateBtn = convertView.findViewById(R.id.edit_student_button);
            ImageButton saveBtn = convertView.findViewById(R.id.save_student_button);

            DAOStudent dao = new DAOStudent();

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dao.delete(a.id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
                                Log.e("ERROR", task.getException().getMessage());
                            }
                        }
                    });
                }
            });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name.setFocusable(true);
                    name.setEnabled(true);
                    name.setClickable(true);
                    name.setFocusableInTouchMode(true);
                    studentId.setFocusable(true);
                    studentId.setEnabled(true);
                    studentId.setClickable(true);
                    studentId.setFocusableInTouchMode(true);
                }
            });

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameText = name.getText().toString().trim();
                    String idText = studentId.getText().toString().trim();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("name", nameText);
                    hashMap.put("studentId", idText);

                    dao.update(a.getId(), hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
                                name.setFocusable(false);
                                name.setEnabled(false);
                                name.setClickable(false);
                                name.setFocusableInTouchMode(false);
                                studentId.setFocusable(false);
                                studentId.setEnabled(false);
                                studentId.setClickable(false);
                                studentId.setFocusableInTouchMode(false);
                            } else {
                                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
                                Log.e("ERROR", task.getException().getMessage());
                            }
                        }
                    });
                }
            });
        }
        return  convertView;
    }
}
