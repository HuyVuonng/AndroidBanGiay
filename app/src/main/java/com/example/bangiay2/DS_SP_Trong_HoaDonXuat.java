package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bangiay2.Adapter.DSHangTrongHoaDonNhapAdapter;
import com.example.bangiay2.Adapter.DSHangTrongHoaDonXuatAdapter;
import com.example.bangiay2.Class.ChiTiethoaDonXuat;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class DS_SP_Trong_HoaDonXuat extends AppCompatActivity {
    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    String mahdNhan;
    int mahd;
    ArrayList<ChiTiethoaDonXuat> arrayList;
    DSHangTrongHoaDonXuatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_sp_trong_hoa_don_xuat);

        lv = findViewById(R.id.lvDSHangHoaDonXuat);
        arrayList = new ArrayList<>();
        adapter = new DSHangTrongHoaDonXuatAdapter(this,R.layout.dong_ds_hang_trong_hd_nhap,arrayList);
        lv.setAdapter(adapter);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        mahdNhan=getIntent().getStringExtra("maHDXuat");
        mahd= Integer.parseInt(mahdNhan);
        hienthiDL();
    }

    private void hienthiDL() {
        Cursor dataHangHDXuat = database.GetData("SELECT * FROM ChiTietHoaDonXuat WHERE maHD='"+mahd+"'");
        arrayList.clear();
        while (dataHangHDXuat.moveToNext()) {
            int maHD = dataHangHDXuat.getInt(0);
            int Sl=dataHangHDXuat.getInt(4);
            float gia= dataHangHDXuat.getFloat(5);
            String maHang=dataHangHDXuat.getString(2);
            String tenHang=dataHangHDXuat.getString(3);
            String ngaytaoHD = dataHangHDXuat.getString(1);
            arrayList.add(new ChiTiethoaDonXuat(maHD,Sl,maHang,tenHang,ngaytaoHD,gia));
        }
        adapter.notifyDataSetChanged();
    }
}