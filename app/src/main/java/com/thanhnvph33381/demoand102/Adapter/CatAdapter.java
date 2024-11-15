package com.thanhnvph33381.demoand102.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thanhnvph33381.demoand102.DAO.CatDAO;
import com.thanhnvph33381.demoand102.DTO.CatDTO;
import com.thanhnvph33381.demoand102.R;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<CatDTO> list;
    CatDAO catDAO;

    public CatAdapter(Context context, ArrayList<CatDTO> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= LayoutInflater.from(context);
        view = inflater.inflate(R.layout.category,viewGroup,false);
        TextView txttensp = view.findViewById(R.id.txttensp);
        Button btnxoa=view.findViewById(R.id.btnDelete);
        CatDTO user=list.get(i);
        txttensp.setText(user.getName());
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
