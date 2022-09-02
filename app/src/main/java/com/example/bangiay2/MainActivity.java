package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                Toast.makeText(MainActivity.this, "Nhập hàng", Toast.LENGTH_LONG).show();
            }
        });

        xuathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Xuất hàng", Toast.LENGTH_LONG).show();
            }
        });

        hoadonnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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