package com.thanhnvph33381.demoand102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thanhnvph33381.demoand102.Adapter.ProAdapter;
import com.thanhnvph33381.demoand102.DAO.ProDAO;
import com.thanhnvph33381.demoand102.DTO.ProDTO;

import java.util.ArrayList;

public class Product extends AppCompatActivity {

    RecyclerView rv_product;
    ProDAO proDAO;
    ArrayList<ProDTO> list=new ArrayList<>();
    ProAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        
        // lấy dữ liệu
        rv_product = findViewById(R.id.recycle_product);
        proDAO = new ProDAO(this);
        FloatingActionButton floatAdd_product = findViewById(R.id.floatAdd_product);
        FloatingActionButton floatRe_product = findViewById(R.id.floatRe_product);
        
        // lấy danh sách dữ liệu
        list = proDAO.getListPro();
        adapter = new ProAdapter(this, list, proDAO);
        
        // hiển thị dữ liệu lên màn hình
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_product.setLayoutManager(linearLayoutManager);
        rv_product.setAdapter(adapter);

        //
        floatRe_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}