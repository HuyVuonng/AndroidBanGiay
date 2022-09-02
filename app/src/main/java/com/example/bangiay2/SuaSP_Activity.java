package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiay2.Database.DatabaseQuanLy;

public class SuaSP_Activity extends AppCompatActivity {

    Intent intent;
    String tenspsua,maspsua,slspsua;
    EditText edtTensp,edtSL;
    Button btnThaydoi,btnHuy;
    DatabaseQuanLy database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sp);

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        tenspsua = getIntent().getStringExtra("tenSpSua");
        maspsua = getIntent().getStringExtra("maSpSua");
        slspsua = getIntent().getStringExtra("SLSPSua");

        edtTensp=findViewById(R.id.editTextTenSanPhamSua);
        edtSL=findViewById(R.id.editTextSoLuongSanPhamSua);
        btnThaydoi=findViewById(R.id.btnCapNhapSua);
        btnHuy=findViewById(R.id.btnHuySua);

        edtTensp.setText(tenspsua);
        edtSL.setText(slspsua);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SuaSP_Activity.this,HangTrongKho_Activity.class);
                startActivity(intent);
            }
        });

        btnThaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmoi = edtTensp.getText().toString().trim();
                String SLmoi= edtSL.getText().toString().trim();
                int SLmoiINT= Integer.parseInt(SLmoi);


                if(TextUtils.isEmpty(tenmoi)||TextUtils.isEmpty(SLmoi)){
                    Toast.makeText(SuaSP_Activity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    database.QuerryData("UPDATE Hang SET TENlOAIGIAY='"+tenmoi+"',Sl='"+SLmoiINT+"'WHERE MAHANG='"+maspsua+"'");
                    Toast.makeText(SuaSP_Activity.this,"Cập nhật thành công!!",Toast.LENGTH_LONG).show();
                    intent=new Intent(SuaSP_Activity.this,HangTrongKho_Activity.class);
                    startActivity(intent);
                }
            }
        });

    }
}