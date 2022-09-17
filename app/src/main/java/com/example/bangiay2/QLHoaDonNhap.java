package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import java.util.Collections;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bangiay2.Adapter.DSHoaDonNhapAdapter;
import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
        Cursor dataHDNHap = database.GetData("SELECT * FROM HoaDonNhap order by maHD DESC");
        Cursor dataChitietHoadonNhap = database.GetData("SELECT maHD, SUM(GiaNhap) as TONGTIENNHAP,SUM(Sl) as TONGSOSP,maHang FROM ChiTietHoaDonNhap group by maHD,maHang order by maHD DESC");
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
            arrayList.add(new HoaDonNhap(ngaytaoHD,maHD,TONGTIENNHAP));

        }
//Sắp xếp tổng tiền giảm dần
        for(int i=0;i<arrayList.size();i++){
            for(int j=i+1;j<arrayList.size();j++){
                if(arrayList.get(i).getTongtien() < arrayList.get(j).getTongtien()){
                    HoaDonNhap tg1;
                    tg1=arrayList.get(i);
                    arrayList.set(i,arrayList.get(j));
                    arrayList.set(j,tg1);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}