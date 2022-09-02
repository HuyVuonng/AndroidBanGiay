package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Class.TaiKhoan;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {

    DatabaseQuanLy database;
    EditText tenDN,mk;
    TextView quenMK;
    Button dangky,dangnhap;
    Intent intent;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        taiKhoanArrayList= new ArrayList<>();
        //Tao DB
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS User (ID INTEGER PRIMARY KEY AUTOINCREMENT,TenDN VARCHAR(50),MatKhau VARCHAR (25))");

//        database.QuerryData("INSERT INTO User VALUES(null,'huy','1234567')");
        anhxa();
        user = getIntent().getStringExtra("userreg");
        tenDN.setText(user);
        pass = getIntent().getStringExtra("passreg");
        mk.setText(pass);
        getdata();


        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mk1=mk.getText().toString();
                String tenDN1= tenDN.getText().toString();
                if(tenDN.equals("")||mk.equals("")){
                    Toast.makeText(DangNhap.this, "Vui lòng điền tất cả thông tin", Toast.LENGTH_LONG).show();

                }
                else{
                    boolean trangthaitaikhoan=false;
                    for(int i =0; i< taiKhoanArrayList.size(); i++){
                        if(tenDN1.equals(taiKhoanArrayList.get(i).getUsername()) && mk1.equals(taiKhoanArrayList.get(i).getPassword())){
                            Intent intent = new Intent(DangNhap.this,MainActivity.class);
                            startActivity(intent);
                            trangthaitaikhoan=true;
                            break;
                        }
                        else{
                            trangthaitaikhoan=false;
                        }
                    }
                    if(trangthaitaikhoan==true){
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(DangNhap.this, "Chưa có tài khoản này hoặc sai mật khẩu", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        quenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DangNhap.this,QuenMK.class);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Cursor dataTaiKhoan = database.GetData("SELECT * FROM User ");
        taiKhoanArrayList.clear();
        while (dataTaiKhoan.moveToNext()) {
            int id= dataTaiKhoan.getInt(0);
            String TenDN = dataTaiKhoan.getString(1);
            String MatKhau = dataTaiKhoan.getString(2);
            taiKhoanArrayList.add(new TaiKhoan(id,TenDN,MatKhau));
        }
    }

    private void anhxa() {
        tenDN=findViewById(R.id.editTextTenDN);
        mk=findViewById(R.id.editTextMatKhauDN);
        dangky=findViewById(R.id.btnDangKy);
        dangnhap=findViewById(R.id.btnDangNhap);
        quenMK=findViewById(R.id.tvquenmk);
    }




}