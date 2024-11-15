package com.thanhnvph33381.demoand102.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thanhnvph33381.demoand102.DTO.CatDTO;
import com.thanhnvph33381.demoand102.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    public CatDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    // hàm thêm dữ liệu
    public int AddRow (CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName());
        int kq= (int) db.insert("tb_cat", null, values);
        return kq;
    }
    public ArrayList<CatDTO> getList(){
        ArrayList<CatDTO> list = new ArrayList<>();
        String sql = "select id, name from tb_cat";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0){
            // lay du lieu
            cursor.moveToFirst();
            // duyet vong lap
            do{
                //Thứ tự cột : id là 0 , name là 1
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                CatDTO objCat = new CatDTO();
                objCat.setId(id);
                objCat.setName(name);
                list.add(objCat);
            }while (cursor.moveToNext());

        }else {
            Log.d("xxxxx", "getList: khong co du lieu");
        }
        return list;
    }
    public CatDTO getOneById( int id){
        CatDTO objCat = null;
        String [] params = { String.valueOf( id ) };
        Cursor c = db.rawQuery("SELECT id, name FROM tb_cat WHERE id = ? ", params);
        if(c!=null && c.getCount() ==1){
            objCat = new CatDTO();// khởi tạo đối tượng
            objCat.setId(  c.getInt(0)  );
            objCat.setName( c.getString(1) );
        }
        return  objCat;
    }
    public boolean updateRow(CatDTO objCat){
        // tạo đối tượng truyền dữ liệu vào bảng
        ContentValues v = new ContentValues();
        v.put("name", objCat.getName() );
        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objCat.getId()  ) };
        // thực thi lệnh cập nhật
        long kq = db.update("tb_cat", v,"id = ?", dieu_kien );
        return kq > 0; // nếu update thành công thì kq >0
    }
    public boolean deleteRow (CatDTO objCat){
        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objCat.getId()  ) };
        long kq = db.delete("tb_cat", "id = ?", dieu_kien );
        return kq>0;
    }

}
