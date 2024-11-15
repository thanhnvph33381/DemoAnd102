package com.thanhnvph33381.demoand102;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

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
    CatDAO catDAO;
    String TAG="xxxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        catDAO = new CatDAO(this);
        CatDTO objcat = new CatDTO();
        objcat.setName("Oto");
        //ghi vao CSDL
        int kq = catDAO .AddRow(objcat);
        if (kq==-1){
            Log.d(TAG , "onCreate: Loi khong them duoc");
            }else {
            Log.d(TAG , "onCreate: Them thanh cong id = "+kq);
        }
        //---- lấy danh sách ----//
        ArrayList<CatDTO> listCat = catDAO.getList();
        //dùng vòng lặp for để xem kết quả
        for(int i = 0; i < listCat.size(); i++){
            
            Log.d(TAG , "onCreate: phan tu thu "+ i +"la:" + listCat.get(i).toString());
        }
    }
}