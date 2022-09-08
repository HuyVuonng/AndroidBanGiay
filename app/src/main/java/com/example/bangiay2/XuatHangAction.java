package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Class.ChiTiethoaDonXuat;
import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Class.HoaDonXuat;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class XuatHangAction extends AppCompatActivity {
    String maSp,tenSp,SlSP,Gia;
    Intent intent;
    int SLSPCON;
    DatabaseQuanLy database;
    ArrayList<Hang> arrayList;
    ArrayList<HoaDonXuat> arrayListHoaDonXuat;
    ArrayList<ChiTiethoaDonXuat> arrayListChiTietHoaDonXuat;
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


        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonXuat (maHD INTEGER ,NgayTao VARCHAR(50),maHang VARCHAR(50),tenHang VARCHAR(50),Sl INTEGER,GiaXuat Float)");

        arrayList= new ArrayList<>();
        arrayListChiTietHoaDonXuat= new ArrayList<>();
        arrayListHoaDonXuat= new ArrayList<>();

        getdata();
        getdataHoaDonXuat();

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
                            int SlconLaiSauXuat=SLSPCON-SLXuatINT;
                            int maHD=arrayListHoaDonXuat.get(arrayListHoaDonXuat.size()-1).getMaHoaDon();
                            String NgaylapHD=arrayListHoaDonXuat.get(arrayListHoaDonXuat.size()-1).getNgayTaoHoaDon().toString().trim();

                            float giaXuatFloat= Float.parseFloat(Gia);
                            database.QuerryData("UPDATE Hang SET Sl='"+SlconLaiSauXuat+"' WHERE MAHANG='"+maSp+"'");
                            database.QuerryData("INSERT INTO ChiTietHoaDonXuat VALUES('"+maHD+"','"+NgaylapHD+"','"+maSp+"','"+tenSp+"','"+SLXuatINT+"','"+giaXuatFloat+"')");
                            Toast.makeText(XuatHangAction.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                            intent= new Intent(XuatHangAction.this,XuatKhoActivity.class);
                            startActivity(intent);


                    }
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

    private void getdataHoaDonXuat(){
        Cursor dataHoaDonXuat = database.GetData("SELECT * FROM HoaDonXuat ");
        arrayList.clear();
        while (dataHoaDonXuat.moveToNext()) {
            int maHD = dataHoaDonXuat.getInt(0);
            String ngayNhap = dataHoaDonXuat.getString(1);
            arrayListHoaDonXuat.add(new HoaDonXuat(ngayNhap,maHD));
        }
    }
}