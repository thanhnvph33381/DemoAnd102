package com.thanhnvph33381.demoand102.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
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
import androidx.recyclerview.widget.RecyclerView;

import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DAO.ProDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;
import com.thanhnvph33381.demoand102.DTO.ProDTO;
import com.thanhnvph33381.demoand102.R;

import java.util.ArrayList;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ViewHolderPro> {
    private final Context context;
    private final ArrayList<ProDTO> list;
    private final ProDAO proDAO;

    public ProAdapter(Context context, ArrayList<ProDTO> list, ProDAO proDAO) {
        this.context = context;
        this.list = list;
        this.proDAO = proDAO;
    }

    @NonNull
    @Override
    public ViewHolderPro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate( R.layout.item_product, null);
        ViewHolderPro holder = new ViewHolderPro(view);
        return new ViewHolderPro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPro holder, int position) {
        holder.txttensp.setText(list.get(position).getName());
        holder.txtgiasp.setText(list.get(position).getPrice());
        ProDTO objPro = list.get(position);
        holder.btn_sua_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thực hiện sửa
                Dialog_Sua_Pro(objPro);

            }
            });
        holder.btn_xoa_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thực hiện xoá
                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (proDAO.deleteRowPro(objPro)) {
                            list.clear();
                            list.addAll(proDAO.getListPro());
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

    public static class ViewHolderPro extends RecyclerView.ViewHolder {
        TextView txttensp , txtgiasp;
        Button btn_sua_product, btn_xoa_product;
        public ViewHolderPro(View itemView) {
            super(itemView);
            txttensp = itemView.findViewById(R.id.txttensp);
            txtgiasp = itemView.findViewById(R.id.txtgiasp);

        }
    }
    public void Dialog_Sua_Pro (ProDTO objPro){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_sua_product, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        EditText edt_ten = view.findViewById(R.id.edt_ten);
        EditText edt_gia = view.findViewById(R.id.edt_gia);
        Button btn_save_product = view.findViewById(R.id.btn_save_product);
        edt_ten.setText(objPro.getName());
        edt_gia.setText(objPro.getPrice());
        btn_save_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objPro.setName(edt_ten.getText().toString());
                objPro.setPrice(Integer.parseInt(edt_gia.getText().toString()));
                if (proDAO.updateRowPro(objPro)) {
                    list.clear();
                    list.addAll(proDAO.getListPro());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
