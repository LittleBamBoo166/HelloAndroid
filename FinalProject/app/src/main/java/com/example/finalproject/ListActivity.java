package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    static public ArrayList<Thi> danhSachThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);
        danhSachThi = new ArrayList<>();
        danhSachThi.add(new Thi("Thì hiện tại đơn", "Hiện tại đơn là thì được sử dụng để diễn tả một thói quen/hành động lặp đi lặp lại trong hiện tại hoặc chân lý, điều hiển nhiên"));
        danhSachThi.add(new Thi("Thì quá khứ đơn", "Thì quá khứ đơn được dùng cho những hành động đã hoàn tất trong quá khứ ở một thời điểm nhất định"));
        danhSachThi.add(new Thi("Thì tương lai đơn", "Thì tương lai đơn trong tiếng Anh (Simple future tense) được dùng khi không có kế hoạch hay quyết định làm gì nào trước khi chúng ta nói"));
        danhSachThi.add(new Thi("Thì hiện tại hoàn thành", "Thì hiện tại hoàn thành là thì sử dụng để diễn tả một hành động đã hoàn thành cho tới thời điểm hiện tại mà không đề cập tới nó xảy ra khi nào"));
        danhSachThi.add(new Thi("Thì quá khứ hoàn thành", "Thì quá khứ hoàn thành dùng để diễn tả một hành động xảy ra trước một hành động khác và cả hai hành động này đều đã xảy ra trong quá khứ"));
        danhSachThi.add(new Thi("Thì tương lai hoàn thành", "Thì tương lai hoàn thành (Future Perfect) là thì dùng để diễn tả một hành động, sự việc sẽ được hoàn thành trước một thời điểm nhất định trong tương lai"));

        MyAdapter myAdapter = new MyAdapter(this, 0, danhSachThi);
        listView.setAdapter(myAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ListDetailActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}