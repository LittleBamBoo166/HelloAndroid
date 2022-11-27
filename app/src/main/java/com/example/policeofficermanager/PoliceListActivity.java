package com.example.policeofficermanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PoliceListActivity extends AppCompatActivity {
    ListView policeLv;
    ArrayList<PoliceOfficer> policeOfficerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_list);

        policeLv = findViewById(R.id.police_list_view);
        policeOfficerArrayList = new ArrayList<>();
        policeOfficerArrayList.add(new PoliceOfficer("https://vcdn1-giaitri.vnecdn.net/2020/03/06/TaylorSwift-1583461602-2814-1583461788.jpg?w=900&h=540&q=100&dpr=1&fit=crop&s=ymu7j1flALRopksgyZhc7w", "Taylor Swift", "3", "America", "5 stars", "White House"));
        policeOfficerArrayList.add(new PoliceOfficer("https://images.mubicdn.net/images/cast_member/45165/cache-390511-1623410063/image-w856.jpg?size=800x", "Miley Cirus", "3", "America", "5 stars", "White House"));
        policeOfficerArrayList.add(new PoliceOfficer("https://yt3.ggpht.com/3gNYUHYh_WgMrDy0CQ06Z_S4Px0UgfNFwAOFUPFF3QiELv1d-KqmmC7NgMq2B8cbSQU4eePU=s900-c-k-c0x00ffffff-no-rj", "Maria Carey", "3", "America", "5 stars", "White House"));
        policeOfficerArrayList.add(new PoliceOfficer("http://hanoimoi.com.vn/Uploads/images/tuandiep/2022/02/12/ro.jpg", "Ronaldo", "3", "America", "5 stars", "White House"));
        policeOfficerArrayList.add(new PoliceOfficer("https://nld.mediacdn.vn/291774122806476800/2022/11/2/hinh-1-16673774006931263959105.jpg", "Messi", "3", "America", "5 stars", "White House"));
        policeOfficerArrayList.add(new PoliceOfficer("https://pyxis.nymag.com/v1/imgs/568/068/6910578e0debd75f945e212a7cf5a92e47-04-enemy.rsquare.w700.jpg", "Miley Cirus", "3", "America", "5 stars", "White House"));

        PoliceOfficerAdapter adapter = new PoliceOfficerAdapter(PoliceListActivity.this, 0, policeOfficerArrayList);
        policeLv.setAdapter(adapter);
    }
}