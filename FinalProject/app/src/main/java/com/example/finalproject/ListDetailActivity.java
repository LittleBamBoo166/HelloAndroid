package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListDetailActivity extends AppCompatActivity {
    int id;
    TextView tenThi, danhSachViDu, detail;
    EditText themViDu;
    Button saveBtn;
    String viDu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        detail = findViewById(R.id.detail_text);
        tenThi = findViewById(R.id.list_text);
        danhSachViDu = findViewById(R.id.danh_sach_vi_du);
        themViDu = findViewById(R.id.editTextNhapViDu);
        saveBtn = findViewById(R.id.buttonSave);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Thi thi = ListActivity.danhSachThi.get(id);
        tenThi.setText(thi.getName());
        detail.setText(thi.getDetail());
        viDu = "Danh sách ví dụ\n" + thi.danhSachViDuToString();
        danhSachViDu.setText(viDu);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viDu = themViDu.getText().toString().trim();
                if (viDu.isEmpty()) {
                    themViDu.setError("Vui lòng nhập ví dụ");
                    themViDu.requestFocus();
                } else {
                    ListActivity.danhSachThi.get(id).themViDu(viDu);
                    viDu = "Danh sách ví dụ\n" + thi.danhSachViDuToString();
                    danhSachViDu.setText(viDu);
                    Toast.makeText(ListDetailActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}