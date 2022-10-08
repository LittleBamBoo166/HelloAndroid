package com.example.apptonghop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends ArrayAdapter<Album> {
    private Context ct;
    private ArrayList<Album> arr;

    public AlbumAdapter(@NonNull Context context, int resource, @NonNull List<Album> objects) {
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
            Album a = arr.get(position);

            ImageView imgAlbum = convertView.findViewById(R.id.imgAlbum);
            imgAlbum.setImageResource(a.image);

            TextView nameAlbum = convertView.findViewById(R.id.nameAlbum);
            nameAlbum.setText(a.name);

            TextView numberSongs = convertView.findViewById(R.id.numberSongs);
            numberSongs.setText(a.numItem + " songs");
        }
        return  convertView;
    }
}
