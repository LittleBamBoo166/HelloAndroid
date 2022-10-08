package com.example.apptonghop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Random;

public class AlbumList extends AppCompatActivity {
    BottomNavigationView navView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SettingFragment settingFragment = new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        navView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();
                        return true;
                    case R.id.action_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag, profileFragment).commit();
                        return true;
                    case R.id.action_setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}