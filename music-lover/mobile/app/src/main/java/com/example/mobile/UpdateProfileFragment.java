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

public class UpdateProfileFragment extends Fragment {
    Context context;
    MaterialButton updateBtn;
    EditText nameTv, addressTv, yearTv;

    public UpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTv.getText().toString();
                String address = addressTv.getText().toString();
                String yearActive = yearTv.getText().toString();
                if (name.equals("") || address.equals("") || yearActive.equals("")) {
                    updateProfile(name, address, yearActive);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_profile, container, false);
        updateBtn = v.findViewById(R.id.profile_update_btn);
        nameTv = v.findViewById(R.id.profile_name);
        addressTv = v.findViewById(R.id.singer_address);
        yearTv = v.findViewById(R.id.singer_year_active);

        Bundle bundle = this.getArguments();
        String name = bundle.getString("name");
        String address = bundle.getString("address");
        String yearActive = bundle.getString("yearActive");
        nameTv.setText(name);
        addressTv.setText(address);
        yearTv.setText(yearActive);
        return v;
    }

    private void updateProfile(String name, String address, String yearActive) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://10.0.3.2:3000/singers/" + MainActivity.USER_ID;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("address", address);
            jsonObject.put("yearActivate", yearActive);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String id = response.getString("id");
                        Toast.makeText(context, "Update profile successful", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(context, "Update profile failed", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Update profile failed", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}