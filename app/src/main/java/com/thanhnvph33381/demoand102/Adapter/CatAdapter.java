package com.thanhnvph33381.demoand102.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;
import com.thanhnvph33381.demoand102.R;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolderCat>{
    private final Context context;
    private final ArrayList<CatDTO> list;
    private final CatDAO catDAO;

    public CatAdapter(Context context, ArrayList<CatDTO> list, CatDAO catDAO) {
        this.context = context;
        this.list = list;
        this.catDAO = catDAO;
    }

    @NonNull
    @Override
    public ViewHolderCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_category, null);
        ViewHolderCat holder = new ViewHolderCat(view);
        return new ViewHolderCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCat holder, int position) {

        holder.txtloaisp.setText(list.get(position).getName());
        CatDTO objCat = list.get(position);
        holder.btn_sua_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thực hiện sửa
                Dialog_Sua_Cat(objCat);

            }
        });
        holder.btn_xoa_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thực hiện xoá
                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (catDAO.deleteRowCat(objCat)){
                            list.clear();
                            list.addAll(catDAO.getListCat());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Delete thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Delete thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog dialog = builder.create();
                dialog.show();

            }

        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderCat extends RecyclerView.ViewHolder {
        TextView txtloaisp;
        Button btn_sua_category, btn_xoa_category;
        public ViewHolderCat(@NonNull View itemView) {
            super(itemView);
            txtloaisp = itemView.findViewById(R.id.txtloaisp);
            btn_sua_category = itemView.findViewById(R.id.btn_sua_category);
            btn_xoa_category = itemView.findViewById(R.id.btn_xoa_category);

        }
    }
    public void Dialog_Sua_Cat(CatDTO objCat){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = View.inflate(context, R.layout.dialog_sua_category, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText edt_loai = view.findViewById(R.id.edt_loai);
        Button btn_save_category = view.findViewById(R.id.btn_save_category);
        edt_loai.setText(objCat.getName());
        btn_save_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objCat.setName(edt_loai.getText().toString());
                if(catDAO.updateRowCat(objCat)){
                    list.clear();
                    list.addAll(catDAO.getListCat());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
