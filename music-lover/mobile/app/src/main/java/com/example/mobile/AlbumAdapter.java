package com.example.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

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
            Glide.with(convertView).load(a.imageUrl).circleCrop().into(imgAlbum);
            TextView nameAlbum = convertView.findViewById(R.id.nameAlbum);
            nameAlbum.setText(a.name);

            TextView singerName = convertView.findViewById(R.id.numberSongs);
            singerName.setText(MainActivity.USER_NAME);

            TextView albumid = convertView.findViewById(R.id.album_id);
            albumid.setText(a.id);
        }
        return  convertView;
    }
}
