package com.example.greenmanagement;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOTree {
    private DatabaseReference databaseReference;

    public DAOTree() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Tree.class.getSimpleName());
    }

    public Task<Void> add (Tree student) {
        return databaseReference.push().setValue(student);
    }

    public Task<Void> update (String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> delete (String key) {
        return databaseReference.child(key).removeValue();
    }
}
