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
import com.example.bangiay2.Adapter.DSHoaDonXuatAdapter;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Class.HoaDonXuat;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class QLHoaDonXuat extends AppCompatActivity {
    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    ArrayList<HoaDonXuat> arrayList;
    DSHoaDonXuatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhoa_don_xuat);

        lv = findViewById(R.id.lvDSHoaDonXuat);
        arrayList = new ArrayList<>();
        adapter = new DSHoaDonXuatAdapter(this,R.layout.dong_hoa_don_xuat,arrayList);
        lv.setAdapter(adapter);


        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String maHD=Integer.toString(arrayList.get(i).getMaHoaDon());
                intent =new Intent(QLHoaDonXuat.this,DS_SP_Trong_HoaDonXuat.class);
                intent.putExtra("maHDXuat",maHD);
                startActivity(intent);
                Toast.makeText(QLHoaDonXuat.this,maHD,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hienthiDL() {
        Cursor dataHDNHap = database.GetData("SELECT * FROM HoaDonXuat order by maHD DESC");
        Cursor dataChitietHoadonNhap = database.GetData("SELECT maHD, SUM(GiaXuat) as TONGTIENNHAP,SUM(Sl) as TONGSOSP,maHang FROM ChiTietHoaDonXuat group by maHD,maHang order by maHD DESC");
        arrayList.clear();
        while (dataHDNHap.moveToNext()) {
            float TONGTIENNHAP=0;
            float TONGTIENMATHANG=0;
            int maHD = dataHDNHap.getInt(0);
            String ngaytaoHD = dataHDNHap.getString(1);
            while(dataChitietHoadonNhap.moveToNext()){
                int maHDCHitiet=dataChitietHoadonNhap.getInt(0);
                if(maHDCHitiet==maHD){
                    TONGTIENMATHANG=dataChitietHoadonNhap.getFloat(1)*dataChitietHoadonNhap.getInt(2);;
                    TONGTIENNHAP+=TONGTIENMATHANG;

                }
            }
            dataChitietHoadonNhap.moveToFirst();
            arrayList.add(new HoaDonXuat(ngaytaoHD,maHD,TONGTIENNHAP));
        }
        adapter.notifyDataSetChanged();
    }
}