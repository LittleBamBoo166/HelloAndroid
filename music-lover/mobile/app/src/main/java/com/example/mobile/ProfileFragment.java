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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileFragment extends Fragment {
    Context context;
    TextView singerNameTv, numberOfAlbumsTv, addressTv, yearActiveTv;
    MaterialButton updateBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        getSinger();
        getNumberOfAlbums();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", singerNameTv.getText().toString());
                bundle.putString("address", addressTv.getText().toString());
                bundle.putString("yearActive", yearActiveTv.getText().toString());
                UpdateProfileFragment updateProfileFragment = new UpdateProfileFragment();
                updateProfileFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.containerFrag, updateProfileFragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        singerNameTv = view.findViewById(R.id.profile_singer_name);
        numberOfAlbumsTv = view.findViewById(R.id.number_of_albums);
        addressTv = view.findViewById(R.id.profile_place);
        yearActiveTv = view.findViewById(R.id.year_active);
        updateBtn = view.findViewById(R.id.update_profile);

        return view;
    }

    private void getSinger() {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/singers/" + MainActivity.USER_ID;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String name = response.getString("name");
                        singerNameTv.setText(name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        String address = response.getString("address");
                        addressTv.setText(address);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        int yearActivate = response.getInt("yearActivate");
                        yearActiveTv.setText("" + yearActivate);
                    } catch (JSONException e) {
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

    private void getNumberOfAlbums() {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/singers/" + MainActivity.USER_ID + "/number-of-albums";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int numberOfAlbums = response.getInt("numberOfAlbums");
                        numberOfAlbumsTv.setText(numberOfAlbums + " albums");
                    } catch (JSONException e) {
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
}