package com.thanhnvph33381.demoand102.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(Context context) {
        super(context,"QuanLyBanHang.db",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng tb_cat
        String sqlCategory = "CREATE TABLE tb_cat (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE NOT NULL);";
        db.execSQL(sqlCategory);

        // Tạo bảng tb_product
        String sqlProduct = "CREATE TABLE tb_pro (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE NOT NULL, " +
                "price REAL DEFAULT (0.0) NOT NULL, " +
                "id_cat INTEGER REFERENCES tb_cat (id));";
        db.execSQL(sqlProduct);

        // Thêm dữ liệu mẫu vào bảng tb_cat
        String[] categories = {"Điện tử", "Thời trang", "Đồ chơi", "Thực phẩm", "Đồ gia dụng"};
        for (String name : categories) {
            db.execSQL("INSERT INTO tb_cat (name) VALUES ('" + name + "');");
        }

        // Thêm dữ liệu mẫu vào bảng tb_product
        db.execSQL("INSERT INTO tb_pro (name, price, id_cat) VALUES ('iPhone', 999.99, 1);");
        db.execSQL("INSERT INTO tb_pro (name, price, id_cat) VALUES ('Áo thun', 19.99, 2);");
        db.execSQL("INSERT INTO tb_pro (name, price, id_cat) VALUES ('Lego', 49.99, 3);");
        db.execSQL("INSERT INTO tb_pro (name, price, id_cat) VALUES ('Bánh quy', 4.99, 4);");
        db.execSQL("INSERT INTO tb_pro (name, price, id_cat) VALUES ('Nồi cơm điện', 79.99, 5);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_cat");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_pro");
            onCreate(sqLiteDatabase);
        }
    }
}
