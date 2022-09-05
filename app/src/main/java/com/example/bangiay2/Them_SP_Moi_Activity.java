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

import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class Them_SP_Moi_Activity extends AppCompatActivity {

    Intent intent;
    DatabaseQuanLy database;
    EditText maSPThem,tenSPThem,SlSpThem;
    int slCu,slTong;
    String tensp,masp,slsp;
    Button btnThem,btnHuy;
    ArrayList<Hang> arrayList;
    ArrayList<HoaDonNhap> arrayListHoaDonNhap;
    ArrayList<ChitietHoaDonNhap> arrayListChiTietHoaDonNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp_moi);

        arrayList= new ArrayList<>();
        arrayListHoaDonNhap= new ArrayList<>();
        arrayListChiTietHoaDonNhap= new ArrayList<>();

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonNhap (maHD INTEGER ,NgayTao VARCHAR(50),maHang VARCHAR(50),tenHang VARCHAR(50),Sl INTEGER)");


        maSPThem=findViewById(R.id.editTextMaSanPhamThemMoi);
        tenSPThem=findViewById(R.id.editTextTenSanPhamThemMoi);
        SlSpThem=findViewById(R.id.editTextSoLuongSanPhamThemMoi);
        btnThem=findViewById(R.id.btnThemSPMoiVaoKho);
        btnHuy=findViewById(R.id.btnHuyThemSPMoi);

        getdata();
        getdataHoaDonNhap();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =new Intent(Them_SP_Moi_Activity.this,NhapHangActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maHangThem,tenHangThem,slhangThem;
                int slhangThemINT;

                boolean slDangSo=false;


                maHangThem=maSPThem.getText().toString().trim();
                tenHangThem=tenSPThem.getText().toString().trim();
                slhangThem=SlSpThem.getText().toString().trim();
                try {
                    Integer.parseInt(slhangThem);
                    slDangSo=true;
                }
                catch (NumberFormatException e){
//
                    slDangSo=false;
                }

                if(TextUtils.isEmpty(maHangThem)||TextUtils.isEmpty(tenHangThem)||TextUtils.isEmpty(slhangThem)){
                    Toast.makeText(Them_SP_Moi_Activity.this,"Hãy nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{

                    if(slDangSo){
                        slhangThemINT=Integer.parseInt(slhangThem);



                            int maHD=arrayListHoaDonNhap.get(arrayListHoaDonNhap.size()-1).getMaHoaDon();
                            String NgaylapHD=arrayListHoaDonNhap.get(arrayListHoaDonNhap.size()-1).getNgayTaoHoaDon().toString().trim();


                            database.QuerryData("INSERT INTO Hang VALUES('"+maHangThem+"','"+tenHangThem+"','"+slhangThemINT+"')");
                            database.QuerryData("INSERT INTO ChiTietHoaDonNhap VALUES('"+maHD+"','"+NgaylapHD+"','"+maHangThem+"','"+tenHangThem+"','"+slhangThemINT+"')");
                            Toast.makeText(Them_SP_Moi_Activity.this,"Them Sp moi thành công",Toast.LENGTH_LONG).show();
                            intent= new Intent(Them_SP_Moi_Activity.this,NhapHangActivity.class);
                            startActivity(intent);


                    }

                    else{
                        Toast.makeText(Them_SP_Moi_Activity.this,"Số lượng phải là dạng số",Toast.LENGTH_LONG).show();
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

    private void getdataHoaDonNhap(){
        Cursor dataHoaDonNhap = database.GetData("SELECT * FROM HoaDonNhap ");
        arrayList.clear();
        while (dataHoaDonNhap.moveToNext()) {
            int maHD = dataHoaDonNhap.getInt(0);
            String ngayNhap = dataHoaDonNhap.getString(1);
            arrayListHoaDonNhap.add(new HoaDonNhap(ngayNhap,maHD));
        }
    }
}