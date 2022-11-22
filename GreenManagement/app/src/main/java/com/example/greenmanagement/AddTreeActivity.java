package com.example.greenmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class AddTreeActivity extends AppCompatActivity {
    EditText tenThuongGoiEt, tenKhoaHocEt, dacTinhEt, mauLaEt, linkHinhAnhEt;
    MaterialButton addBtn;
    DAOTree dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tree);

        tenThuongGoiEt = findViewById(R.id.add_ten_thuong_goi);
        tenKhoaHocEt = findViewById(R.id.add_ten_khoa_hoc);
        dacTinhEt = findViewById(R.id.add_dac_tinh);
        mauLaEt = findViewById(R.id.add_mau_la);
        linkHinhAnhEt = findViewById(R.id.add_link_hinh_anh);
        addBtn = findViewById(R.id.add_tree_material_btn);

        dao = new DAOTree();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTree();
            }
        });
    }

    private void addTree() {
        String tenThuongGoi = tenThuongGoiEt.getText().toString().trim();
        String tenKhoaHoc = tenKhoaHocEt.getText().toString().trim();
        String dacTinh = dacTinhEt.getText().toString().trim();
        String mauLa = mauLaEt.getText().toString().trim();
        String linkHinhAnh = linkHinhAnhEt.getText().toString().trim();

        if (tenThuongGoi.isEmpty()) {
            tenThuongGoiEt.setError("Vui long dien ten thuong goi");
            tenThuongGoiEt.requestFocus();
            return;
        }

        if (tenKhoaHoc.isEmpty()) {
            tenKhoaHocEt.setError("Vui long dien ten khoa hoc");
            tenKhoaHocEt.requestFocus();
            return;
        }

        if (dacTinh.isEmpty()) {
            dacTinhEt.setError("Vui long dien dac tinh");
            dacTinhEt.requestFocus();
            return;
        }

        if (mauLa.isEmpty()) {
            mauLaEt.setError("Vui long dien mau la");
            mauLaEt.requestFocus();
            return;
        }

        if (linkHinhAnh.isEmpty()) {
            linkHinhAnhEt.setError("Vui long dien link hinh anh");
            linkHinhAnhEt.requestFocus();
            return;
        }


        Tree tree = new Tree(tenKhoaHoc, tenThuongGoi, dacTinh, mauLa, linkHinhAnh);
        dao.add(tree).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddTreeActivity.this, "Add tree successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddTreeActivity.this, "Add tree failed", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", task.getException().getMessage());
                }
            }
        });
    }
}