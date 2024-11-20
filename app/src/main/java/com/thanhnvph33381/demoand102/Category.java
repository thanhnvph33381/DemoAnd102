package com.thanhnvph33381.demoand102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thanhnvph33381.demoand102.Adapter.CatAdapter;
import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    RecyclerView rv_category;
    CatDAO catDAO;
    ArrayList<CatDTO> list=new ArrayList<>();
    CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        // lấy dữ liệu
        rv_category = findViewById(R.id.recycle_category);
        catDAO = new CatDAO(this);
        FloatingActionButton floatAdd_category = findViewById(R.id.floatAdd_category);
        FloatingActionButton floatRe_category = findViewById(R.id.floatRe_category);

        // lấy danh sách dữ liệu
        list = catDAO.getListCat();
        adapter = new CatAdapter(this, list, catDAO);
        // hiển thị dữ liệu lên màn hình
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_category.setLayoutManager(linearLayoutManager);
        rv_category.setAdapter(adapter);
        floatRe_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                startActivity(intent);
            }
        });
        floatAdd_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Category.this);
                View v = getLayoutInflater().inflate(R.layout.dialog_them_category, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

                EditText edt_add_loai = v.findViewById(R.id.edt_add_loai);
                Button btn_add_category = v.findViewById(R.id.btn_add_category);
                btn_add_category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String loai = edt_add_loai.getText().toString();
                        if (loai.isEmpty()) {
                            edt_add_loai.setError("Vui lòng nhập tên loại sản phẩm");
                            edt_add_loai.requestFocus();
                        } else {
                            CatDTO catDTO = new CatDTO(0, loai);
                            if (catDAO.AddRowCat(catDTO) > 0) {
                                list.clear();
                                list.addAll(catDAO.getListCat());
                                adapter.notifyDataSetChanged();

                                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });


            }
        });
    }

}