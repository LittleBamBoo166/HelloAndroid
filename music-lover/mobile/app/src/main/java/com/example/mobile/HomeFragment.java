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
import android.widget.ImageButton;
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
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView albumListView;
    AlbumDetailFragment albumDetailFragment;
    Context context;
    ArrayList<Album> albumArrayList;
    ImageButton addAlbumBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumListView = (ListView) view.findViewById(R.id.albumListView);
        context = view.getContext();

        getAlbums();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        addAlbumBtn = (ImageButton) v.findViewById(R.id.home_add_album);
        addAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAlbumFragment addAlbumFragment = new AddAlbumFragment();
                getFragmentManager().beginTransaction().replace(R.id.containerFrag, addAlbumFragment).commit();
            }
        });
        return v;
    }

    private void getAlbums() {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/singers/" + MainActivity.USER_ID + "/albums";

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    albumArrayList = new ArrayList<>();
                    Log.i("INFO", "TOTAL # of Albums ::: " + Integer.toString(response.length()));
                    for (int i = 0; i < response.length(); ++i) {
                        try {
                            JSONObject responseJson = response.getJSONObject(i);

                            String id = responseJson.getString("id");
                            String name = responseJson.getString("name");
                            String link = responseJson.getString("link");
                            String imageUrl = responseJson.getString("imageUrl");
                            albumArrayList.add(new Album(id, name, link, imageUrl));
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                    AlbumAdapter adapter = new AlbumAdapter(context, 0, albumArrayList);
                    albumListView.setAdapter(adapter);
                    albumListView.setClickable(true);
                    albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView albumIdTextView = view.findViewById(R.id.album_id);
                            String albumId = albumIdTextView.getText().toString();
                            Bundle bundle = new Bundle();
                            bundle.putString("albumId", albumId);
                            albumDetailFragment = new AlbumDetailFragment();
                            albumDetailFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.containerFrag, albumDetailFragment).commit();
                        }
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}