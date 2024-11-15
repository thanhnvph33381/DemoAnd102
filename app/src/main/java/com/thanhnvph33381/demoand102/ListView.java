package com.thanhnvph33381.demoand102;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.thanhnvph33381.demoand102.Adapter.CatAdapter;
import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {
    CatDAO catDAO;
    CatAdapter catAdapter;
    ArrayList<CatDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.lstds);
        catDAO = new CatDAO(this);
        list = catDAO.getList();
//        catAdapter = new CatAdapter(this, R.layout.item_cat, list);
    }
}
