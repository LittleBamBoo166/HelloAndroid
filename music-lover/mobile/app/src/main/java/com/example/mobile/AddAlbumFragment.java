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
import android.widget.EditText;
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

public class AddAlbumFragment extends Fragment {
    EditText nameTv, linkTv, imageTv;
    MaterialButton addBtn;
    Context context;

    public AddAlbumFragment() {
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
                String link = linkTv.getText().toString();
                String img = imageTv.getText().toString();
                if (name.equals("") || link.equals("") || img.equals("")) {
                    Toast.makeText(context, "Please fill out the form", Toast.LENGTH_SHORT).show();
                } else {
                    addAlbum(name, link, img);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_album, container, false);
        nameTv = v.findViewById(R.id.add_album_name);
        linkTv = v.findViewById(R.id.add_album_link);
        imageTv = v.findViewById(R.id.add_album_img);
        addBtn = v.findViewById(R.id.add_album_add_btn);
        return v;
    }

    private void addAlbum(String name, String link, String img) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/singers/" + MainActivity.USER_ID + "/albums";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("link", link);
            jsonObject.put("imageUrl", img);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String id = response.getString("id");
                        Toast.makeText(context, "Adding album successful", Toast.LENGTH_SHORT).show();
                        HomeFragment homeFragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.containerFrag, homeFragment).commit();
                    } catch (JSONException e) {
                        Toast.makeText(context, "Adding album failed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Adding album failed", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}