package com.example.bangiay2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangiay2.Class.ChiTiethoaDonXuat;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.R;

import java.util.List;

public class DSHangTrongHoaDonXuatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChiTiethoaDonXuat> ChiTietHoadonXuatList;

    public DSHangTrongHoaDonXuatAdapter(Context context, int layout, List<ChiTiethoaDonXuat> chiTietHoadonXuatList) {
        this.context = context;
        this.layout = layout;
        ChiTietHoadonXuatList = chiTietHoadonXuatList;
    }

    @Override
    public int getCount() {
        return ChiTietHoadonXuatList.size();
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
        TextView tensp,masp,sl,gia;

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
            viewHolder.gia=view.findViewById(R.id.giaspTRongdsHoaDon);

            view.setTag(viewHolder);


        }
        else{
            viewHolder= (ViewHolder)view.getTag();
        }
        ChiTiethoaDonXuat HD= ChiTietHoadonXuatList.get(i);
        String sl=Integer.toString(HD.getSoLuong());
        String giaXuat= Float.toString(HD.getGiaXuat());
        viewHolder.tensp.setText(HD.getTenHang());
        viewHolder.masp.setText("Mã SP: "+HD.getMaHang());
        viewHolder.sl.setText("Sl: "+sl);
        viewHolder.gia.setText("Giá: "+giaXuat);
        return view;
    }
}
