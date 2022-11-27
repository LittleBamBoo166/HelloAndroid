package com.example.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumDetailFragment extends Fragment {
    Context context;
    ListView trackListView;
    ArrayList<Track> trackArrayList;
    String ALBUM_ID;
    TextView albumNameTv, numberOfSongTv, albumLink, albumId;
    ImageView albumImage;
    MaterialButton deleteAlbumBtn, addTrackBtn;

    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trackListView = (ListView) view.findViewById(R.id.track_list_view);
        context = view.getContext();

        deleteAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlbum(ALBUM_ID);
            }
        });

        addTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("albumId", ALBUM_ID);

                AddTrackFragment addTrackFragment = new AddTrackFragment();
                addTrackFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.containerFrag, addTrackFragment).commit();
            }
        });

        getAlbumById(ALBUM_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album_detail, container, false);
        Bundle bundle = this.getArguments();
        ALBUM_ID = bundle.getString("albumId");
        albumNameTv = v.findViewById(R.id.album_name);
        numberOfSongTv = v.findViewById(R.id.number_of_songs);
        albumLink = v.findViewById(R.id.album_link);
        albumImage = v.findViewById(R.id.imgAlbum);
        albumId = v.findViewById(R.id.album_id);
        deleteAlbumBtn = v.findViewById(R.id.delete_album);
        addTrackBtn = v.findViewById(R.id.add_track);
        return v;
    }

    private void getAlbumById(String id) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/albums/" + id;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    trackArrayList = new ArrayList<>();
                    try {
                        String id = response.getString("id");
                        String name = response.getString("name");
                        String link = response.getString("link");
                        String imageUrl = response.getString("imageUrl");
                        Glide.with(context).load(imageUrl).circleCrop().into(albumImage);
                        albumNameTv.setText(name);
                        albumId.setText(id);
                        albumLink.setText(link);

                        JSONArray trackListJsonArray = response.getJSONArray("trackList");
                        for (int i = 0; i < trackListJsonArray.length(); i++) {
                            JSONObject track = trackListJsonArray.getJSONObject(i);
                            String trackId = track.getString("id");
                            String trackName = track.getString("name");
                            int trackLength = track.getInt("length");
                            ArrayList<String> writers = new ArrayList<>();
                            JSONArray writerJson = track.getJSONArray("writer");
                            for (int j = 0; j < writerJson.length(); j++) {
                                writers.add(writerJson.getString(j));
                            }
                            trackArrayList.add(new Track(trackId, trackName, trackLength, writers));
                        }

                        TrackAdapter adapter = new TrackAdapter(context, 0, trackArrayList);
                        trackListView.setAdapter(adapter);
                        trackListView.setClickable(true);
                        trackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                TextView trackIdTv = view.findViewById(R.id.track_id);
                                String trackId = trackIdTv.getText().toString();
                                deleteTrack(trackId);
                            }
                        });
                    } catch (JSONException e) {
                        Toast.makeText(context, "Load data failed ...", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Load data failed ...", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteTrack(String id) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/albums/tracks/" + id;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    HomeFragment albumDetailFragment = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.containerFrag, albumDetailFragment).commit();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Delete track successful", Toast.LENGTH_SHORT).show();
                    HomeFragment homeFragment = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAlbum(String id) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/albums/" + id;
            Log.d("DEBUG", "URL ::::: " + url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    HomeFragment homeFragment = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Delete album successful", Toast.LENGTH_SHORT).show();
                    HomeFragment homeFragment = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}