package com.example.bangiay2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.NhapHangActivity;
import com.example.bangiay2.R;
import com.example.bangiay2.Them_SP_Activity;
import com.example.bangiay2.XuatHangAction;
import com.example.bangiay2.XuatKhoActivity;

import java.util.List;

public class XuatHangAdapter extends BaseAdapter {
    private XuatKhoActivity context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public XuatHangAdapter(XuatKhoActivity context, int layout, List<Hang> hangList) {
        this.context = context;
        this.layout = layout;
        HangList = hangList;
    }

    @Override
    public int getCount() {
        return HangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolderNhap{
        TextView tvTenSPXuat,tvMaSPXuat,tvSLCoTRongKho,tvGia;
        ImageView imgXuat;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderNhap viewHolder;
        if(view==null){
            viewHolder= new ViewHolderNhap();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tvTenSPXuat= view.findViewById(R.id.tensptrongkhonsptrongkhoXuat);
            viewHolder.tvMaSPXuat=view.findViewById(R.id.masptrongkhoXuat);
            viewHolder.tvSLCoTRongKho=view.findViewById(R.id.soluongsptrongkhoXuat);
            viewHolder.imgXuat=view.findViewById(R.id.btnXuatSP);
            viewHolder.tvGia=view.findViewById(R.id.giaspsptrongkhoXuat);

        }
        else{
            viewHolder= (ViewHolderNhap) view.getTag();
        }
        Hang Sp= HangList.get(i);
        String SL=Integer.toString(Sp.getSoLuong());
        String gia=Float.toString(Sp.getGia());
        viewHolder.tvTenSPXuat.setText(Sp.getTenHang());
        viewHolder.tvMaSPXuat.setText("Mã SP: "+Sp.getMaHang());
        viewHolder.tvSLCoTRongKho.setText("SL: "+SL);
        viewHolder.tvGia.setText("Giá: "+gia);


        viewHolder.imgXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSpXuat= Sp.getTenHang();
                String maSpXuat= Sp.getMaHang();
                int SLSp= Sp.getSoLuong();
                float GiaBan=Sp.getGia();
                String GiaBanString=Float.toString(GiaBan);
                String SlSpCoTrongKho=Integer.toString(SLSp);
                intent =new Intent(context, XuatHangAction.class);
                intent.putExtra("tenSpXuat",tenSpXuat);
                intent.putExtra("maSpXuat",maSpXuat);
                intent.putExtra("GiaBanString",GiaBanString);
                intent.putExtra("SlSpCoTrongKho",SlSpCoTrongKho);
                context.startActivity(intent);

            }
        });
        return view;
    }
}
