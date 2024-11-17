package com.thanhnvph33381.demoand102;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// 1. Khai báo đối tượng
Button btn_category, btn_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn_category=findViewById(R.id.btn_category);
        btn_product=findViewById(R.id.btn_product);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Category.class);
                startActivity(i);
            }
        });
        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Product.class);
                startActivity(i);

            }
        });

    }


}