package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bangiay2.Database.DatabaseQuanLy;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    DatabaseQuanLy database;
    RelativeLayout hangtrongkho,nhaphang,xuathang,hoadonnhap,hoadonxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hangtrongkho=findViewById(R.id.hangtrongkho);
        nhaphang=findViewById(R.id.nhaphang);
        xuathang=findViewById(R.id.xuathang);
        hoadonnhap=findViewById(R.id.hoadonnhap);
        hoadonxuat=findViewById(R.id.hoadonxuat);


        //Tao DB
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS HoaDonNhap (maHD INTEGER PRIMARY KEY AUTOINCREMENT,NgayTao VARCHAR(50))");

        hangtrongkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainActivity.this,HangTrongKho_Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hàng trong kho", Toast.LENGTH_LONG).show();
            }
        });

        nhaphang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog= new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_themhoadon_nhap);
                dialog.show();


                EditText ngayTaoHoaDon= dialog.findViewById(R.id.editTextNgayLapHoaDonNhap);
                Button btnTaoHoaDon= dialog.findViewById(R.id.buttonThemHoaDonNhap);
                Button btnHuyTaoHoaDon= dialog.findViewById(R.id.buttonHuyTaoHoaDonNhap);



                btnHuyTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnTaoHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayTaoNhap=ngayTaoHoaDon.getText().toString().trim();
                        if(TextUtils.isEmpty(ngayTaoNhap)){
                            Toast.makeText(MainActivity.this, "Hãy nhập ngày tạo hóa đơn", Toast.LENGTH_LONG).show();

                        }
                        else{
                            database.QuerryData("INSERT INTO HoaDonNhap VALUES(null,'"+ngayTaoNhap+"')");
                            Toast.makeText(MainActivity.this, "Tạo hóa đơn thành công", Toast.LENGTH_LONG).show();
                            intent= new Intent(MainActivity.this,NhapHangActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }

                    }
                });
            }
        });



        xuathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainActivity.this,XuatKhoActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Xuất hàng", Toast.LENGTH_SHORT).show();
            }
        });

        hoadonnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainActivity.this,QLHoaDonNhap.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Hóa đơn nhập", Toast.LENGTH_LONG).show();
            }
        });

        hoadonxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hóa đơn xuất", Toast.LENGTH_LONG).show();
            }
        });

    }
}