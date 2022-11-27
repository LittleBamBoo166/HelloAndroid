package com.example.computermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private ArrayList<Category> categories;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
        this.context = context;
        this.categories = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.computer_list_item, null);
        }
        if (categories.size() > 0) {
            Category category = categories.get(position);

            TextView categoryName = convertView.findViewById(R.id.category);
            categoryName.setText(category.name);
        }
        return  convertView;
    }
}
