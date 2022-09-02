package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiay2.Class.TaiKhoan;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class DangKy extends AppCompatActivity {

    DatabaseQuanLy database;
    EditText tenDK,mk,Nhaplaimk;
    Button dangky,quaylai;
    Intent intent;
    ArrayList<TaiKhoan> arrayTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        arrayTaiKhoan= new ArrayList<>();
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);
        anhXa();
        getdata();

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);

            }
        });



        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentkDk,mkTK,nhaplaimk;
                tentkDk=tenDK.getText().toString();
                mkTK=mk.getText().toString();
                nhaplaimk=Nhaplaimk.getText().toString();

                if (tentkDk.equals("") || mkTK.equals("") || nhaplaimk.equals("") ) {
                    Toast.makeText(DangKy.this, "Vui lòng điền tất cả thông tin", Toast.LENGTH_LONG).show();
                }
                else{
                    if (nhaplaimk.equals(mkTK)){
                        if(arrayTaiKhoan.size()==0){
                            database.QuerryData("INSERT INTO User VALUES(null,'"+tentkDk+"','"+mkTK+"')");
                            intent = new Intent(DangKy.this, DangNhap.class);
                            intent.putExtra("userreg", tentkDk);
                            intent.putExtra("passreg", mkTK);
                            startActivity(intent);
                            Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        }
                        else{
                            for(int i=0;i< arrayTaiKhoan.size();i++){
                                if(tentkDk.equals(arrayTaiKhoan.get(i).getUsername())){
                                    Toast.makeText(DangKy.this, "Đã tồn tại tài khoản này", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    database.QuerryData("INSERT INTO User VALUES(null,'"+tentkDk+"','"+mkTK+"')");
                                    intent = new Intent(DangKy.this, DangNhap.class);
                                    intent.putExtra("userreg", tentkDk);
                                    intent.putExtra("passreg", mkTK);
                                    startActivity(intent);
                                    Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                }
                            }
                        }


                    }
                    else{
                        Toast.makeText(DangKy.this, "Mật khẩu không trùng khớp,vui lòng nhập lại", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

    }

    private void getdata() {
        Cursor dataTaiKhoan = database.GetData("SELECT * FROM User ");
        arrayTaiKhoan.clear();
        while (dataTaiKhoan.moveToNext()) {
            String TenDN = dataTaiKhoan.getString(1);
            String MatKhau = dataTaiKhoan.getString(2);
            int id= dataTaiKhoan.getInt(0);
            arrayTaiKhoan.add(new TaiKhoan(id, TenDN, MatKhau));
        }
    }

    public void anhXa(){
        tenDK=findViewById(R.id.editTextTenDK);
        mk=findViewById(R.id.editTextMatKhauDK);
        Nhaplaimk=findViewById(R.id.editTextNhapLaiMatKhauDk);
        dangky=findViewById(R.id.btnDangKyTK);
        quaylai=findViewById(R.id.btnQuayLai);
    }
}