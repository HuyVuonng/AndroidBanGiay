package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.TaiKhoan;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class Them_SP_Activity extends AppCompatActivity {
    Intent intent;
    DatabaseQuanLy database;
    EditText maSPThem,tenSPThem,SlSpThem;
    int slCu,slTong;
    String tensp,masp,slsp;
    Button btnThem,btnHuy;
    ArrayList<Hang> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp);

        arrayList= new ArrayList<>();

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        tensp = getIntent().getStringExtra("tenSpThem");
        masp = getIntent().getStringExtra("maSpThem");
        slsp = getIntent().getStringExtra("SLSPThem");

        maSPThem=findViewById(R.id.editTextMaSanPhamThem);
        tenSPThem=findViewById(R.id.editTextTenSanPhamThem);
        SlSpThem=findViewById(R.id.editTextSoLuongSanPhamThem);
        btnThem=findViewById(R.id.btnThemSPVaoKho);
        btnHuy=findViewById(R.id.btnHuyThemSP);

        getdata();

        if(!TextUtils.isEmpty(masp)||!TextUtils.isEmpty(tensp)){
            maSPThem.setText(masp);
            tenSPThem.setText(tensp);
        }

//        SlSpThem.setText(slsp);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =new Intent(Them_SP_Activity.this,NhapHangActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maHangThem,tenHangThem,slhangThem;
                int slhangThemINT;
                boolean tontai=false;

                maHangThem=maSPThem.getText().toString().trim();
                tenHangThem=tenSPThem.getText().toString().trim();
                slhangThem=SlSpThem.getText().toString().trim();
                slhangThemINT=Integer.parseInt(slhangThem);

                for(int i=0;i<arrayList.size();i++){
                    if(maHangThem.equals(arrayList.get(i).getMaHang())){
                        tontai=true;
                        break;
                    }
                    else{
                        tontai=false;
                    }
                }

                if(tontai){
                    slCu = Integer.parseInt(slsp);
                    slTong= slCu+slhangThemINT;

                    database.QuerryData("UPDATE Hang SET Sl='"+slTong+"' WHERE MAHANG='"+maHangThem+"'");
                    Toast.makeText(Them_SP_Activity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                    intent= new Intent(Them_SP_Activity.this,NhapHangActivity.class);
                    startActivity(intent);
                }
                else{
                    database.QuerryData("INSERT INTO Hang VALUES('"+maHangThem+"','"+tenHangThem+"','"+slhangThemINT+"')");
                    Toast.makeText(Them_SP_Activity.this,"Them thành công",Toast.LENGTH_LONG).show();
                    intent= new Intent(Them_SP_Activity.this,NhapHangActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void getdata() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang ");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String tenHang = dataHang.getString(1);
            String maHang = dataHang.getString(0);
            arrayList.add(new Hang(maHang, tenHang, SL));
        }
    }
}