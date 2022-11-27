package com.example.policeofficermanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoliceOfficerAdapter extends ArrayAdapter<PoliceOfficer> {
    private Context context;
    private ArrayList<PoliceOfficer> policeOfficerArrayList;


    public PoliceOfficerAdapter(@NonNull Context context, int resource, @NonNull List<PoliceOfficer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.policeOfficerArrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.police_officer_item, null);
        }
        if (policeOfficerArrayList.size() > 0) {
            PoliceOfficer a = policeOfficerArrayList.get(position);

            ImageView policeImg = convertView.findViewById(R.id.police_img);
            TextView policeName = convertView.findViewById(R.id.police_name);
            TextView policeWorkPlace = convertView.findViewById(R.id.work_place);
            TextView policeStarNumber = convertView.findViewById(R.id.starNumber);
            TextView policeLevel = convertView.findViewById(R.id.level);
            TextView policeCountry = convertView.findViewById(R.id.country);

            Glide.with(convertView).load(a.getImageUrl()).circleCrop().into(policeImg);
            policeName.setText(a.getName());
            policeLevel.setText(a.getLevel());
            policeCountry.setText(a.getCountry());
            policeWorkPlace.setText(a.getWorkPlace());
            policeStarNumber.setText(a.getStarNumber());
        }
        return  convertView;
    }
}
