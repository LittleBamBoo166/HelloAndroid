package com.example.apptonghop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView albumListView;
    AlbumDetailFragment albumDetailFragment;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumListView = (ListView) view.findViewById(R.id.albumListView);
        ArrayList<Album> arr = new ArrayList<>();

        arr.add(new Album(0, R.drawable.album1, 15, "Evermore"));
        arr.add(new Album(1, R.drawable.album2, 13, "Fearless"));
        arr.add(new Album(2, R.drawable.album3, 16, "Folklore"));
        arr.add(new Album(3, R.drawable.album4, 18, "Lover"));
        arr.add(new Album(4, R.drawable.album5, 13, "Midnights"));
        arr.add(new Album(5, R.drawable.album6, 16, "Red"));
        arr.add(new Album(6, R.drawable.album7, 15, "Reputation"));
        arr.add(new Album(7, R.drawable.album8, 14, "Speak Now"));
        arr.add(new Album(8, R.drawable.album9, 11, "Taylor Swift"));

        AlbumAdapter adapter = new AlbumAdapter(view.getContext(), 0, arr);
        albumListView.setAdapter(adapter);
        albumListView.setClickable(true);
        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                ArrayList<String> arrSong = new ArrayList<>();
                arrSong.add("Tim McGraw");
                switch (i) {
                    case 0:
                        bundle.putInt("image", R.drawable.album1);
                        bundle.putInt("numItem", 15);
                        bundle.putString("name", "Evermore");
                        break;
                    case 1:
                        bundle.putInt("image", R.drawable.album2);
                        bundle.putInt("numItem", 13);
                        bundle.putString("name", "Fearless");
                        break;
                    case 2:
                        bundle.putInt("image", R.drawable.album3);
                        bundle.putInt("numItem", 16);
                        bundle.putString("name", "Folklore");
                        break;
                    case 3:
                        bundle.putInt("image", R.drawable.album4);
                        bundle.putInt("numItem", 18);
                        bundle.putString("name", "Lover");
                        break;
                    case 4:
                        bundle.putInt("image", R.drawable.album5);
                        bundle.putInt("numItem", 13);
                        bundle.putString("name", "Midnights");
                        break;
                    case 5:
                        bundle.putInt("image", R.drawable.album6);
                        bundle.putInt("numItem", 16);
                        bundle.putString("name", "Red");
                        break;
                    case 6:
                        bundle.putInt("image", R.drawable.album7);
                        bundle.putInt("numItem", 15);
                        bundle.putString("name", "Reputation");
                        break;
                    case 7:
                        bundle.putInt("image", R.drawable.album8);
                        bundle.putInt("numItem", 16);
                        bundle.putString("name", "Speak Now");
                        break;
                    case 8:
                        bundle.putInt("image", R.drawable.album9);
                        bundle.putInt("numItem", 11);
                        bundle.putString("name", "Taylor Swift");
                        break;
                }
                bundle.putStringArrayList("songs", arrSong);
                albumDetailFragment = new AlbumDetailFragment();
                albumDetailFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.containerFrag, albumDetailFragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}