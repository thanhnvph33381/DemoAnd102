package com.thanhnvph33381.demoand102.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thanhnvph33381.demoand102.DTO.ProDTO;
import com.thanhnvph33381.demoand102.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ProDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    public ProDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    // hàm thêm dữ liệu
    public int AddRowPro (ProDTO objPro){
        ContentValues values = new ContentValues();
        values.put("name", objPro.getName());
        values.put("price", objPro.getPrice());
        values.put("id_cat", objPro.getId_cat());
        int kq= (int) db.insert("tb_pro", null, values);
        return kq;
    }
    public ArrayList<ProDTO> getListPro(){
        ArrayList<ProDTO> list = new ArrayList<>();
        String sql = "select id, name, price, id_cat from tb_pro";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0){
            // lay du lieu
            cursor.moveToFirst();
            // duyet vong lap
            do{
                //Thứ tự cột : id là 0 , name là
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int price = cursor.getInt(2);
                int id_cat = cursor.getInt(3);
                ProDTO objPro = new ProDTO();
                objPro.setId(id);
                objPro.setName(name);
                objPro.setPrice(price);
                objPro.setId_cat(id_cat);
                list.add(objPro);
                }while (cursor.moveToNext());
        }
        return list;
    }
    public ProDTO getOneByIdPro( int id){
        ProDTO objPro = null;
        String [] params = { String.valueOf( id ) };
        Cursor c = db.rawQuery("SELECT id, name, price, id_cat FROM tb_pro WHERE id = ? ", params);
        if(c!=null && c.getCount() ==1){
            objPro = new ProDTO();// khởi tạo đối tượng
            objPro.setId(  c.getInt(0)  );
            objPro.setName( c.getString(1) );

            objPro.setPrice( c.getInt(2) );
            objPro.setId_cat( c.getInt(3) );
        }
        return  objPro;
    }
    public boolean updateRowPro(ProDTO objPro){
        // tạo đối tượng truyền dữ liệu vào bảng
        ContentValues v = new ContentValues();
        v.put("name", objPro.getName() );
        v.put("price", objPro.getPrice() );
        v.put("id_cat", objPro.getId_cat() );

        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objPro.getId()  ) };
        // thực thi lệnh cập nhật
        long kq = db.update("tb_pro", v,"id = ?", dieu_kien );
        return kq > 0; // nếu update thành công thì kq >0
    }
    public boolean deleteRowPro (ProDTO objPro){
        // tạo đk update
        String [] dieu_kien = { String.valueOf(  objPro.getId()  ) };
        long kq = db.delete("tb_pro", "id = ?", dieu_kien );
        return kq>0;
    }

}
