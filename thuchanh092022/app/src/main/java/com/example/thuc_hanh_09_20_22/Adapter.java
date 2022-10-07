package com.example.thuc_hanh_09_20_22;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    final ArrayList<Cat> listCat;

    public Adapter(ArrayList<Cat> listCat) {
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int i) {
        return listCat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listCat.get(i).catID;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View catView;
        if (view == null) {
            catView = View.inflate(parent.getContext(), R.layout.cat_view, null);
        } else {
            catView = view;
        }
        Cat cat = (Cat) getItem(i);
        ((TextView) catView.findViewById(R.id.id_cat)).setText(String.format("ID = %d", cat.catID));
        ((TextView) catView.findViewById(R.id.cat_name)).setText(String.format("Cat name: %s", cat.name));
//        ((TextView) catView.findViewById())
        return  catView;
    }
}
