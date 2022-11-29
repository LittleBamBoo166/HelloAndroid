package com.example.finalproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAO {
    private DatabaseReference databaseReference;

    public DAO() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference(Student.class.getSimpleName());
    }

//    public Task<Void> add (Student student) {
//        return databaseReference.push().setValue(student);
//    }

    public Task<Void> update (String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> delete (String key) {
        return databaseReference.child(key).removeValue();
    }
}
