package com.example.mobile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class AddTrackFragment extends Fragment {
    EditText nameTv, lengthTv, writersTv;
    MaterialButton addBtn;
    Context context;
    String ALBUM_ID;

    public AddTrackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTv.getText().toString();
                String length = lengthTv.getText().toString();
                String writer = writersTv.getText().toString();
                if (name.equals("") || length.equals("") || writer.equals("")) {
                    Toast.makeText(context, "Please fill out the form", Toast.LENGTH_SHORT).show();
                } else {
                    addTrack(name, length, writer);
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_track, container, false);
        nameTv = v.findViewById(R.id.add_track_name);
        lengthTv = v.findViewById(R.id.add_track_length);
        writersTv = v.findViewById(R.id.add_track_writers);
        addBtn = v.findViewById(R.id.add_track_add_btn);

        Bundle bundle = this.getArguments();
        ALBUM_ID = bundle.getString("albumId");
        return v;
    }

    private void addTrack(String name, String length, String writers) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/albums/" + ALBUM_ID + "/tracks";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("length", length);
            ArrayList<String> writerArrayList = new ArrayList<>(Arrays.asList(writers.split(";")));
            JSONArray writerJsonArray = new JSONArray();
            for (int i = 0; i < writerArrayList.size(); i++) {
                writerJsonArray.put(writerArrayList.get(i));
            }
            jsonObject.put("writer", writerJsonArray);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String id = response.getString("id");
                        Toast.makeText(context, "Adding track successful", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(context, "Adding track failed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Adding track failed", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}