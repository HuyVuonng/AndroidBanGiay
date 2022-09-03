package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Adapter.NhapHang_Adapter;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class NhapHangActivity extends AppCompatActivity {

    DatabaseQuanLy database;
    NhapHang_Adapter adapter;
    ArrayList<Hang> arrayList;
    ListView lv;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang);

        arrayList=new ArrayList<>();
        lv=findViewById(R.id.lvloaigiayNhap);
        adapter = new NhapHang_Adapter(this,R.layout.dong_nhap_hang,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        ImageView themSpmoi= findViewById(R.id.imageViewThemSPMoi);
        themSpmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(NhapHangActivity.this,Them_SP_Activity.class);
                 startActivity(intent);
            }
        });
    }

    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            arrayList.add(new Hang(MaHang, TenHang, SL));
        }
        adapter.notifyDataSetChanged();
    }
}