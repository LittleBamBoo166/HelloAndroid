package com.example.greenmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TreeListActivity extends AppCompatActivity {
    ImageButton addTreeBtn;
    ListView treeListView;
    ArrayList<Tree> treeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);

        treeArrayList = new ArrayList<>();
        treeListView = findViewById(R.id.tree_list_view);
        addTreeBtn = findViewById(R.id.add_tree_btn);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tree");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Tree tree = snapshot.getValue(Tree.class);
                    String key = snapshot.getKey();
                    tree.setKey(key);
                    treeArrayList.add(tree);
                }
                TreeAdapter adapter = new TreeAdapter(TreeListActivity.this, 0, treeArrayList);
                Log.d("MESSAGE", "" + treeArrayList.size());
                treeListView.setAdapter(adapter);
                treeListView.setClickable(true);

                addTreeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(TreeListActivity.this, AddTreeActivity.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TreeListActivity.this, "Failed to show the tree list", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", databaseError.getMessage());
            }
        });
    }
}