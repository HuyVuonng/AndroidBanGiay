package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class HangTrongKho_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ListView lv;
    Intent intent;
    ArrayList<Hang> arrayList;
    Hang_Adapter adapter;
    EditText edtTimKiem;
    RadioButton tangdan,giamdan;
    Button btnTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_trong_kho);


        lv = findViewById(R.id.lvloaigiay);
        edtTimKiem=findViewById(R.id.edtTimKiem);
//        btnTim=findViewById(R.id.btnTimKiemhangTRongKho);
        tangdan=findViewById(R.id.rdoGiaTang);
        giamdan=findViewById(R.id.rdoGiaGiam);
        arrayList = new ArrayList<>();
        adapter = new Hang_Adapter(this,R.layout.dong_hang_trong_kho,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS Hang (MAHANG varchar(50) PRIMARY KEY , TENlOAIGIAY VARCHAR(200),Sl INTEGER,Gia Float)");

        //Them DULieu

//       database.QuerryData("INSERT INTO Hang VALUES('MH1','Giày thể thao',5,10000)");
//       database.QuerryData("INSERT INTO Hang VALUES('MH2','Giày NIKE',10,20000)");

        hienthiDL();


//        btnTim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String NDTIM= edtTimKiem.getText().toString().trim();
//                if(TextUtils.isEmpty(NDTIM)){
//                    Toast.makeText(HangTrongKho_Activity.this,"Bạn chưa nhập nội dung tìm kiếm",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG Like '%"+NDTIM+"%' or TENlOAIGIAY Like '%"+NDTIM+"%' ");
//                arrayList.clear();
//                while (dataHang.moveToNext()) {
//                    int SL = dataHang.getInt(2);
//                    String TenHang = dataHang.getString(1);
//                    String MaHang = dataHang.getString(0);
//                    float Gia=dataHang.getInt(3);
//                    arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
//                }
//                adapter.notifyDataSetChanged();
//                adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
//                lv.setAdapter(adapter);
//
//            }
//        });


        //Tìm kiếm trực tiếp khi nhập vào edt mà không phải nhấn tìm
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String NDTIM= edtTimKiem.getText().toString().trim();
                if(TextUtils.isEmpty(NDTIM)){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        float Gia=dataHang.getInt(3);
                        arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                    return;
                }


                Cursor dataHang = database.GetData("SELECT * FROM Hang WHERE MAHANG Like '%"+NDTIM+"%' or TENlOAIGIAY Like '%"+NDTIM+"%' ");
                arrayList.clear();
                while (dataHang.moveToNext()) {
                    int SL = dataHang.getInt(2);
                    String TenHang = dataHang.getString(1);
                    String MaHang = dataHang.getString(0);
                    float Gia=dataHang.getInt(3);
                    arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                }
                adapter.notifyDataSetChanged();
                adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                lv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        giamdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(giamdan.isChecked()){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang order by Gia DESC");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        float Gia=dataHang.getInt(3);
                        arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                }
            }
        });

        tangdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tangdan.isChecked()){
                    Cursor dataHang = database.GetData("SELECT * FROM Hang order by Gia ASC");
                    arrayList.clear();
                    while (dataHang.moveToNext()) {
                        int SL = dataHang.getInt(2);
                        String TenHang = dataHang.getString(1);
                        String MaHang = dataHang.getString(0);
                        float Gia=dataHang.getInt(3);
                        arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
                    }
                    adapter.notifyDataSetChanged();
                    adapter= new Hang_Adapter(HangTrongKho_Activity.this,R.layout.dong_hang_trong_kho,arrayList);
                    lv.setAdapter(adapter);
                }
            }
        });
    }


    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            float Gia=dataHang.getInt(3);
            arrayList.add(new Hang(MaHang,TenHang,SL,Gia));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogXoa(String tenSp,String maHang){
        Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa);
        dialog.show();

        TextView textXacNhanSPXoa= dialog.findViewById(R.id.XacNhanXoaTXT);

        Button btnXoa=dialog.findViewById(R.id.buttonXacNhanXoa);
        Button btnHuyXoa=dialog.findViewById(R.id.buttonHuy);


        textXacNhanSPXoa.setText("Bạn có cắc chắn muốn xóa "+tenSp+"?");


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    database.QuerryData("Delete from Hang WHERE MAHANG='"+maHang+"'");
                    Toast.makeText(HangTrongKho_Activity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    intent= new Intent(HangTrongKho_Activity.this,HangTrongKho_Activity.class);
                    startActivity(intent);
//                    hienthiDL();
            }
        });


        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }
}
