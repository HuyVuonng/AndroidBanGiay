package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bangiay2.Adapter.DSHangTrongHoaDonNhapAdapter;
import com.example.bangiay2.Adapter.DSHoaDonNhapAdapter;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class DS_SP_Trong_HoaDonNhap extends AppCompatActivity {

    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    String mahdNhan;
    int mahd;
    ArrayList<ChitietHoaDonNhap> arrayList;
    DSHangTrongHoaDonNhapAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_sp_trong_hoa_don_nhap);

        lv = findViewById(R.id.lvDSHangHoaDonNhap);
        arrayList = new ArrayList<>();
        adapter = new DSHangTrongHoaDonNhapAdapter(this,R.layout.dong_ds_hang_trong_hd_nhap,arrayList);
        lv.setAdapter(adapter);

        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        mahdNhan=getIntent().getStringExtra("maHDNhap");
        mahd= Integer.parseInt(mahdNhan);
        hienthiDL();
    }

    private void hienthiDL() {
        Cursor dataHangHDNHap = database.GetData("SELECT * FROM ChiTietHoaDonNhap WHERE maHD='"+mahd+"'");
        arrayList.clear();
        while (dataHangHDNHap.moveToNext()) {
            int maHD = dataHangHDNHap.getInt(0);
            int Sl=dataHangHDNHap.getInt(4);
            String maHang=dataHangHDNHap.getString(2);
            String tenHang=dataHangHDNHap.getString(3);
            String ngaytaoHD = dataHangHDNHap.getString(1);
            arrayList.add(new ChitietHoaDonNhap(maHD,Sl,maHang,tenHang,ngaytaoHD));
        }
        adapter.notifyDataSetChanged();

    }
}