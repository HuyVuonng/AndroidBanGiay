package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangiay2.Class.ChitietHoaDonNhap;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Class.HoaDonNhap;
import com.example.bangiay2.Class.TaiKhoan;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class Them_SP_Activity extends AppCompatActivity {
    Intent intent;
    DatabaseQuanLy database;
    EditText maSPThem,tenSPThem,SlSpThem,GiaSpThem;
    int slCu,slTong;
    String tensp,masp,slsp;
    Button btnThem,btnHuy;
    ArrayList<Hang> arrayList;
    ArrayList<HoaDonNhap> arrayListHoaDonNhap;
    ArrayList<ChitietHoaDonNhap> arrayListChiTietHoaDonNhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp);

        arrayList= new ArrayList<>();
        arrayListHoaDonNhap= new ArrayList<>();
        arrayListChiTietHoaDonNhap= new ArrayList<>();

        database= new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite",null,1);

        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS ChiTietHoaDonNhap (maHD INTEGER ,NgayTao VARCHAR(50),maHang VARCHAR(50),tenHang VARCHAR(50),Sl INTEGER,GiaNhap Float)");

        tensp = getIntent().getStringExtra("tenSpThem");
        masp = getIntent().getStringExtra("maSpThem");
        slsp = getIntent().getStringExtra("SLSPThem");

        maSPThem=findViewById(R.id.editTextMaSanPhamThem);
        tenSPThem=findViewById(R.id.editTextTenSanPhamThem);
        SlSpThem=findViewById(R.id.editTextSoLuongSanPhamThem);
        GiaSpThem=findViewById(R.id.editTextGiaSanPhamThem);
        btnThem=findViewById(R.id.btnThemSPVaoKho);
        btnHuy=findViewById(R.id.btnHuyThemSP);

        getdata();
        getdataHoaDonNhap();

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
                String maHangThem,tenHangThem,slhangThem,giaSPNhap;
                int slhangThemINT;
                float giaHagThemFloat,giaban;
                int tontai=0;
                boolean slDangSo=false;


                maHangThem=maSPThem.getText().toString().trim();
                tenHangThem=tenSPThem.getText().toString().trim();
                slhangThem=SlSpThem.getText().toString().trim();
                giaSPNhap=GiaSpThem.getText().toString().trim();

                try {
                    Integer.parseInt(slhangThem);
                    Float.parseFloat(giaSPNhap);
                    slDangSo=true;
                }
                catch (NumberFormatException e){
//
                    slDangSo=false;
                }

                if(TextUtils.isEmpty(maHangThem)||TextUtils.isEmpty(tenHangThem)||TextUtils.isEmpty(slhangThem)||TextUtils.isEmpty(giaSPNhap)){
                    Toast.makeText(Them_SP_Activity.this,"Hãy nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{

                    if(slDangSo){
                        slhangThemINT=Integer.parseInt(slhangThem);
                        giaHagThemFloat=Float.parseFloat(giaSPNhap);

                        float lai=giaHagThemFloat/10;

                        giaban=giaHagThemFloat+lai;
                        int vitri=0;

                        for(int i=0;i<arrayList.size();i++){
                            if(maHangThem.equalsIgnoreCase(arrayList.get(i).getMaHang().toString().trim())){
                                tontai=1;
                                vitri=i;
                                break;
                            }
                        }


                        if(tontai==1){


                                slCu=Integer.parseInt(slsp);


                            slTong= slCu+slhangThemINT;
                            int maHD=arrayListHoaDonNhap.get(arrayListHoaDonNhap.size()-1).getMaHoaDon();
                            String NgaylapHD=arrayListHoaDonNhap.get(arrayListHoaDonNhap.size()-1).getNgayTaoHoaDon().toString().trim();

                            database.QuerryData("UPDATE Hang SET Sl='"+slTong+"',Gia= '"+giaban+"' WHERE MAHANG='"+maHangThem+"'");
                            database.QuerryData("INSERT INTO ChiTietHoaDonNhap VALUES('"+maHD+"','"+NgaylapHD+"','"+maHangThem+"','"+tenHangThem+"','"+slhangThemINT+"','"+giaHagThemFloat+"')");
                            Toast.makeText(Them_SP_Activity.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                            intent= new Intent(Them_SP_Activity.this,NhapHangActivity.class);
                            startActivity(intent);

                        }

                        else{
                           Toast.makeText(Them_SP_Activity.this,"Chưa tồn tại mã sản phẩm này",Toast.LENGTH_SHORT).show();
                        }




                    }

                    else{
                        Toast.makeText(Them_SP_Activity.this,"Số lượng và giá phải là dạng số",Toast.LENGTH_LONG).show();
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
        arrayListHoaDonNhap.clear();
        while (dataHoaDonNhap.moveToNext()) {
            int maHD = dataHoaDonNhap.getInt(0);
            String ngayNhap = dataHoaDonNhap.getString(1);
            arrayListHoaDonNhap.add(new HoaDonNhap(ngayNhap,maHD));
        }
    }
}