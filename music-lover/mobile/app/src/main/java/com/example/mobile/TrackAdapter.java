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

public class TrackAdapter extends ArrayAdapter<Track> {
    private Context ct;
    private ArrayList<Track> arr;

    public TrackAdapter(@NonNull Context context, int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.list_track_item, null);
        }
        if (arr.size() > 0) {
            Track track = arr.get(position);

            TextView trackIdTv = convertView.findViewById(R.id.track_id);
            TextView trackOrderTv = convertView.findViewById(R.id.track_order);
            TextView trackNameTv = convertView.findViewById(R.id.track_name);
            TextView trackLengthTv = convertView.findViewById(R.id.track_length);
            TextView trackWriters = convertView.findViewById(R.id.song_writer);

            trackOrderTv.setText("" + (position + 1));
            trackIdTv.setText(track.id);
            trackNameTv.setText(track.name);
            trackLengthTv.setText(track.getTrackLengthString());
            trackWriters.setText(track.getWriterString());
        }
        return  convertView;
    }
}
