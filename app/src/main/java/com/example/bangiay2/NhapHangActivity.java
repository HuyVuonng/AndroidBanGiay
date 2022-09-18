package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Adapter.NhapHang_Adapter;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class NhapHangActivity extends AppCompatActivity {

    DatabaseQuanLy database;
    NhapHang_Adapter adapter;
    ArrayList<Hang> arrayList;
    EditText timkiemsp;


    ListView lv;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang);

        arrayList=new ArrayList<>();


        lv=findViewById(R.id.lvloaigiayNhap);
        timkiemsp=findViewById(R.id.edtTimKiemNhapHang);
        adapter = new NhapHang_Adapter(this,R.layout.dong_nhap_hang,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);

        hienthiDL();

        ImageView themSpmoi= findViewById(R.id.imageViewThemSPMoi);
        themSpmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(NhapHangActivity.this,Them_SP_Moi_Activity.class);
                 startActivity(intent);
            }
        });

        timkiemsp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String NDTIM= timkiemsp.getText().toString().trim();
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
                    adapter= new NhapHang_Adapter(NhapHangActivity.this,R.layout.dong_nhap_hang,arrayList);
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
                adapter= new NhapHang_Adapter(NhapHangActivity.this,R.layout.dong_nhap_hang,arrayList);
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
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            arrayList.add(new Hang(MaHang, TenHang, SL));
        }
        adapter.notifyDataSetChanged();
    }
}