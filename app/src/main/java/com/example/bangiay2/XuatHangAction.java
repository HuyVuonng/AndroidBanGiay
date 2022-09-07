package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XuatHangAction extends AppCompatActivity {
    String maSp,tenSp,SlSP,Gia;
    Intent intent;
    int SLSPCON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_xuat_kho);

        tenSp= getIntent().getStringExtra("tenSpXuat");
        maSp= getIntent().getStringExtra("maSpXuat");
        SlSP= getIntent().getStringExtra("SlSpCoTrongKho");
        Gia= getIntent().getStringExtra("GiaBanString");



        TextView textTenSpXuat= findViewById(R.id.tenHangDangXuat);
        TextView textMaSpXuat= findViewById(R.id.maHangDangXuat);
        TextView textGiaSpXuat= findViewById(R.id.GiaHangDangXuat);
        TextView textSLSPCOn= findViewById(R.id.SLTRongKho);
        EditText edtSLHangXuat= findViewById(R.id.SoluongHangXuat);

        Button btnXuat=findViewById(R.id.btnXuatHang);
        Button btnHuyXuat=findViewById(R.id.btnHuyXuathang);

        textTenSpXuat.setText("TeenSP: "+tenSp);
        textMaSpXuat.setText("Mã: "+maSp);
        textGiaSpXuat.setText("Giá: "+Gia);
        textSLSPCOn.setText("Còn: "+SlSP);

        btnHuyXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(XuatHangAction.this,XuatKhoActivity.class);
                startActivity(intent);
            }
        });

        btnXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SLXuat= edtSLHangXuat.getText().toString().trim();

                if(TextUtils.isEmpty(SLXuat)){
                    Toast.makeText(XuatHangAction.this,"Nhập số lượng muốn xuất kho",Toast.LENGTH_SHORT).show();
                }
                else{
                    String SL= SlSP.toString().trim();

                    SLSPCON = Integer.parseInt(SL);
                        int SLXuatINT=Integer.parseInt(SLXuat);
                        if(SLXuatINT > SLSPCON){
                            Toast.makeText(XuatHangAction.this,"Só lượng xuất lớn hơn số lượng hiện có, mời nhập lại",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(XuatHangAction.this,"Chạy Xử lý",Toast.LENGTH_SHORT).show();


                    }
                }

            }
        });

    }
}