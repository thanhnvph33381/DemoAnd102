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
        floatAdd_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Product.this);
                View v = getLayoutInflater().inflate(R.layout.dialog_them_product, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

                EditText edt_add_ten = v.findViewById(R.id.edt_add_ten);
                EditText edt_add_gia = v.findViewById(R.id.edt_add_gia);
                Button btn_add_product = v.findViewById(R.id.btn_add_product);
                btn_add_product.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ten = edt_add_ten.getText().toString();
                        String gia = edt_add_gia.getText().toString();

                        try {
                            int gia1 = Integer.parseInt(gia);
                            if(ten.isEmpty() ){
                                edt_add_ten.setError("Vui lòng nhập tên sản phẩm");
                                edt_add_ten.requestFocus();
                            } else if (gia.isEmpty()) {
                                edt_add_gia.setError("Vui lòng nhập giá sản phẩm");
                                edt_add_gia.requestFocus();
                            } else if (gia1 < 0) {
                                edt_add_gia.setError("Giá không hợp lệ");
                                edt_add_gia.requestFocus();
                            } else {
                                ProDTO proDTO = new ProDTO(0,ten,gia1,0);
                                if (proDAO.AddRowPro(proDTO)>0){
                                    list.clear();
                                    list.addAll(proDAO.getListPro());
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }catch (NumberFormatException e){
                            Toast.makeText(getApplicationContext(), "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });



    }
}