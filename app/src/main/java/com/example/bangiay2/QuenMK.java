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

public class QuenMK extends AppCompatActivity {

    DatabaseQuanLy database;
    EditText tenDKQuenMK,mkQuenMK,NhaplaimkQuenMK;
    Button thaydoi,quaylai;
    Intent intent;
    ArrayList<TaiKhoan> arrayTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);

        arrayTaiKhoan= new ArrayList<>();
        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);
        anhXa();
        getdata();

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(QuenMK.this, DangNhap.class);
                startActivity(intent);

            }
        });

        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentkDkquenMK,mkTKquenMK,nhaplaimkquenMK;
                tentkDkquenMK=tenDKQuenMK.getText().toString();
                mkTKquenMK=mkQuenMK.getText().toString();
                nhaplaimkquenMK=NhaplaimkQuenMK.getText().toString();

                if (tentkDkquenMK.equals("") || mkTKquenMK.equals("") || nhaplaimkquenMK.equals("") ) {
                    Toast.makeText(QuenMK.this, "Vui lòng điền tất cả thông tin", Toast.LENGTH_LONG).show();
                }
                else{
                    if (nhaplaimkquenMK.equals(mkTKquenMK)){
                        boolean tontai=false;
                        for(int i=0;i< arrayTaiKhoan.size();i++){
                            if(!tentkDkquenMK.equals(arrayTaiKhoan.get(i).getUsername())){
                                tontai=false;

                            }
                            else{
                                database.QuerryData("UPDATE User SET MatKhau='"+mkTKquenMK+"' WHERE TenDN='"+tentkDkquenMK+"'");
                                intent = new Intent(QuenMK.this, DangNhap.class);
                                intent.putExtra("userreg", tentkDkquenMK);
                                intent.putExtra("passreg", mkTKquenMK);
                                startActivity(intent);
                                tontai=true;
                                break;

                            }
                        }

                        if(tontai==true){
                            Toast.makeText(QuenMK.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(QuenMK.this, "Không tồn tại tài khoản này", Toast.LENGTH_LONG).show();
                        }

                    }
                    else{
                        Toast.makeText(QuenMK.this, "Mật khẩu không trùng khớp,vui lòng nhập lại", Toast.LENGTH_LONG).show();

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
        tenDKQuenMK=findViewById(R.id.editTextTenDKQuenMK);
        mkQuenMK=findViewById(R.id.editTextMatKhauDKQuenMK);
        NhaplaimkQuenMK=findViewById(R.id.editTextNhapLaiMatKhauDkQuenMK);
        thaydoi=findViewById(R.id.btnThayDoi);
        quaylai=findViewById(R.id.btnQuayLaiQuenMK);
    }
}