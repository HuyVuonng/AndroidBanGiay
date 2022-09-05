package com.example.bangiay2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.R;

import java.util.List;

public class DSHangTrongHoaDonNhapAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChitietHoaDonNhap> ChiTietHoadonNhapList;


    public DSHangTrongHoaDonNhapAdapter(Context context, int layout, List<ChitietHoaDonNhap> chiTietHoadonNhapList) {
        this.context = context;
        this.layout = layout;
        ChiTietHoadonNhapList = chiTietHoadonNhapList;
    }

    @Override
    public int getCount() {
        return ChiTietHoadonNhapList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tensp,masp,sl;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tensp= view.findViewById(R.id.tenspTRongdsHoaDon);
            viewHolder.masp=view.findViewById(R.id.masptrongHDNHAP);
            viewHolder.sl=view.findViewById(R.id.soluongsptronghdnhap);


        }
        else{
            viewHolder= (ViewHolder)view.getTag();
        }
        ChitietHoaDonNhap HD= ChiTietHoadonNhapList.get(i);
        String sl=Integer.toString(HD.getSoLuong());
        viewHolder.tensp.setText(HD.getTenHang());
        viewHolder.masp.setText("Mã SP: "+HD.getMaHang());
        viewHolder.sl.setText("Sl: "+sl);
        return view;
    }
}
