package com.example.recyclerview_th_oct_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.SelectedUser {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<UserModel> userModelList = new ArrayList<>();
    String[] names = {"Hanbin", "Hyung Seob", "Hyuk", "LEW", "Hwa Rang", "Eun Chan", "Tea Rea"};
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        for (String s: names) {
            UserModel userModel = new UserModel(s);
            userModelList.add(userModel);
        }

        usersAdapter = new UsersAdapter(userModelList, this);
        recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void selectedUser(UserModel userModel) {
        startActivity(new Intent(MainActivity.this, SelectedUserActivity.class).putExtra("data", userModel));
    }
}