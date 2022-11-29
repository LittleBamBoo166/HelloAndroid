package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class MyAdapter extends ArrayAdapter<Thi> {
    private Context context;
    private ArrayList<Thi> thiArrayList;


    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Thi> objects) {
        super(context, resource, objects);
        this.context = context;
        this.thiArrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.list_item, null);
        }
        if (thiArrayList.size() > 0) {
            Thi a = thiArrayList.get(position);

            TextView name = convertView.findViewById(R.id.list_text);
            name.setText(a.getName());
        }

        return  convertView;
    }
}
