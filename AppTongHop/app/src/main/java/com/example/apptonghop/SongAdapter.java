package com.example.apptonghop;

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

public class SongAdapter extends ArrayAdapter<String> {
    private Context ct;
    private ArrayList<String> arr;

    public SongAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.list_album_item, null);
        }
        if (arr.size() > 0) {
            String song = arr.get(position);

            TextView songName = convertView.findViewById(R.id.songName);
            int id = position + 1;
            songName.setText(id + ". " + song);
        }
        return  convertView;
    }
}
