package com.example.bangiay2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Class.TaiKhoan;
import com.example.bangiay2.Database.DatabaseQuanLy;
import com.example.bangiay2.HangTrongKho_Activity;
import com.example.bangiay2.R;

import java.util.ArrayList;
import java.util.List;

public class DSHoaDonNhapAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HoaDonNhap> HoadonList;

    Intent intent;

    public DSHoaDonNhapAdapter(Context context, int layout, List<HoaDonNhap> hoadonList) {
        this.context = context;
        this.layout = layout;
        HoadonList = hoadonList;
    }



    @Override
    public int getCount() {
        return HoadonList.size();
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
        TextView NgayNhap,maHD,tongtien;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.NgayNhap= view.findViewById(R.id.ngayNhapHang);
            viewHolder.maHD=view.findViewById(R.id.maHDNhap);
            viewHolder.tongtien=view.findViewById(R.id.tongsotiennhap);

            view.setTag(viewHolder);



        }
        else{
            viewHolder= (ViewHolder)view.getTag();
        }
        HoaDonNhap HD= HoadonList.get(i);
        String maHD=Integer.toString(HD.getMaHoaDon());
        float TongTienCuaHoaDon= HD.getTongtien();

        String tongTienString= Float.toString(TongTienCuaHoaDon);
        viewHolder.NgayNhap.setText("Ngày tạo: "+HD.getNgayTaoHoaDon());
        viewHolder.maHD.setText("Mã HD: "+maHD);
        viewHolder.tongtien.setText("Tổng tiền: "+tongTienString);
        return view;
    }


}
