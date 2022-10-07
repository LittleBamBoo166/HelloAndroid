package com.example.thuc_hanh_09_20_22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Cat> listCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCat = new ArrayList<>();
        listCat.add(new Cat(1,"Jenifer", 3.75F, "coffee", "Ragdoll Cats"));
        listCat.add(new Cat(2, "Jenifer", 3.75F, "chocolate", "British Shorthair"));
        listCat.add(new Cat(3, "Jenifer", 3.75F, "orange", "Persian Cats"));
        listCat.add(new Cat(4, "Jenifer", 3.75F, "orange", "Maine Coon Cats"));
        listCat.add(new Cat(5, "Jenifer", 3.75F, "black", "Ragdoll Cats"));
        listCat.add(new Cat(6, "Jenifer", 3.75F, "black and white", "American Shorthair Cats"));
        listCat.add(new Cat(7, "Jenifer", 3.75F, "coffee", "Scottish Fold Cats"));
        listCat.add(new Cat(8, "Jenifer", 3.75F, "white", "Sphynx Cats"));
    }
}