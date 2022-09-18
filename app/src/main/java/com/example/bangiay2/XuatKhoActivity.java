package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Adapter.NhapHang_Adapter;
import com.example.bangiay2.Adapter.XuatHangAdapter;
import com.example.bangiay2.Class.ChiTiethoaDonXuat;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Class.HoaDonXuat;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class XuatKhoActivity extends AppCompatActivity {
    DatabaseQuanLy database;
    XuatHangAdapter adapter;
    ArrayList<Hang> arrayList;
    EditText timkiemSp;
    ListView lv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_kho);

        arrayList=new ArrayList<>();
        lv=findViewById(R.id.lvloaigiayXuat);
        timkiemSp=findViewById(R.id.edtTimKiemXuatHang);
        adapter = new XuatHangAdapter(this,R.layout.dong_xuat_kho,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        timkiemSp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String NDTIM= timkiemSp.getText().toString().trim();
                if(TextUtils.isEmpty(NDTIM)){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        float Gia=dataHang.getInt(3);
                        arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new XuatHangAdapter(XuatKhoActivity.this,R.layout.dong_xuat_kho,arrayList);
                    lv.setAdapter(adapter);
                    return;
                }


                Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG Like '%"+NDTIM+"%' or TENlOAIGIAY Like '%"+NDTIM+"%' ");
                arrayList.clear();
                while (dataHang.moveToNext()) {
                    int SL = dataHang.getInt(2);
                    String TenHang = dataHang.getString(1);
                    String MaHang = dataHang.getString(0);
                    float Gia=dataHang.getInt(3);
                    arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                }
                adapter.notifyDataSetChanged();
                adapter= new XuatHangAdapter(XuatKhoActivity.this,R.layout.dong_xuat_kho,arrayList);
                lv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            Float gia=dataHang.getFloat(3);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            arrayList.add(new Hang(MaHang, TenHang, SL,gia));
        }
        adapter.notifyDataSetChanged();
    }

}