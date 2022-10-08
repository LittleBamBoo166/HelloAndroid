package com.example.apptonghop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumDetailFragment extends Fragment {
    ListView songLv;

    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_album_detail, container, false);
        Bundle bundle = this.getArguments();
        int image = bundle.getInt("image");
        int numItem = bundle.getInt("numItem");
        String name = bundle.getString("name");

        ImageView imgAlbum = (ImageView) v.findViewById(R.id.imgAlbum);
        imgAlbum.setImageResource(image);
        TextView nameAlbum = (TextView) v.findViewById(R.id.nameAlbum);
        nameAlbum.setText(name);
        TextView numberSongs = (TextView) v.findViewById(R.id.numberSongs);
        numberSongs.setText(numItem + " songs");
        return v;
    }
}