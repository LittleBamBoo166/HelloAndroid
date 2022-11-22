package com.example.greenmanagement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeAdapter extends ArrayAdapter<Tree> {
    private Context context;
    private ArrayList<Tree> treeArrayList;


    public TreeAdapter(@NonNull Context context, int resource, @NonNull List<Tree> objects) {
        super(context, resource, objects);
        this.context = context;
        this.treeArrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.tree_item, null);
        }
        if (treeArrayList.size() > 0) {
            Tree a = treeArrayList.get(position);

            TextView tenThuongGoiTv = convertView.findViewById(R.id.ten_thuong_goi);
            TextView tenKhoaHocTv = convertView.findViewById(R.id.ten_khoa_hoc);
            TextView key = convertView.findViewById(R.id.tree_key);
            TextView mauLa = convertView.findViewById(R.id.mau_la);
            TextView dacTinh = convertView.findViewById(R.id.dac_tinh);
            mauLa.setText(a.mauLa);
            dacTinh.setText(a.dacTinh);
            key.setText(a.key);
            tenThuongGoiTv.setText(a.tenThuongGoi);
            tenKhoaHocTv.setText(a.tenKhoaHoc);

            ImageButton deleteBtn = convertView.findViewById(R.id.delete_tree_btn);
            ImageButton showBtn = convertView.findViewById(R.id.show_tree_detail_btn);

            DAOTree dao = new DAOTree();

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dao.delete(a.key).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
                                Log.e("ERROR", task.getException().getMessage());
                            }
                        }
                    });
                }
            });
        }
        return  convertView;
    }
}
