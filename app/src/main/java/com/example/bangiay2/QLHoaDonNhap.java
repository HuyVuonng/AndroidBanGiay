package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bangiay2.Adapter.DSHoaDonNhapAdapter;
import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class QLHoaDonNhap extends AppCompatActivity {

    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    ArrayList<HoaDonNhap> arrayList;
    DSHoaDonNhapAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhoa_don_nhap);

        lv = findViewById(R.id.lvDSHoaDonNhap);
        arrayList = new ArrayList<>();
        adapter = new DSHoaDonNhapAdapter(this,R.layout.dong_hoa_don_nhap,arrayList);
        lv.setAdapter(adapter);


        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String maHD=Integer.toString(arrayList.get(i).getMaHoaDon());
                intent =new Intent(QLHoaDonNhap.this,DS_SP_Trong_HoaDonNhap.class);
                intent.putExtra("maHDNhap",maHD);
                startActivity(intent);
                Toast.makeText(QLHoaDonNhap.this,maHD,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void hienthiDL() {
        Cursor dataHDNHap = database.GetData("SELECT * FROM HoaDonNhap");
        arrayList.clear();
        while (dataHDNHap.moveToNext()) {
            int maHD = dataHDNHap.getInt(0);

            String ngaytaoHD = dataHDNHap.getString(1);
            arrayList.add(new HoaDonNhap(ngaytaoHD,maHD));
        }
        adapter.notifyDataSetChanged();
    }
}