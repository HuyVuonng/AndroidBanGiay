package com.example.bangiay2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Adapter.Hang_Adapter;
import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.Database.DatabaseQuanLy;

import java.util.ArrayList;

public class HangTrongKho_Activity extends AppCompatActivity {
    DatabaseQuanLy database;
    ListView lv;
    ArrayList<Hang> arrayList;
    Hang_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_trong_kho);


        lv = findViewById(R.id.lvloaigiay);
        arrayList = new ArrayList<>();
        adapter = new Hang_Adapter(this,R.layout.dong_hang_trong_kho,arrayList);
        lv.setAdapter(adapter);
        //Tao DB
        database = new DatabaseQuanLy(this, "QuanLyBanGiayDn.sqlite", null, 1);


        //Tao bang
        database.QuerryData("CREATE TABLE IF NOT EXISTS Hang (MAHANG varchar(50) PRIMARY KEY , TENlOAIGIAY VARCHAR(200),Sl INTEGER)");

        //Them DULieu

//       database.QuerryData("INSERT INTO Hang VALUES('MH1','Giày thể thao',5)");
//       database.QuerryData("INSERT INTO Hang VALUES('MH2','Giày NIKE',10)");

        hienthiDL();




    }


    private void hienthiDL() {
        Cursor dataHang = database.GetData("SELECT * FROM Hang");
        arrayList.clear();
        while (dataHang.moveToNext()) {
            int SL = dataHang.getInt(2);
            String TenHang = dataHang.getString(1);
            String MaHang = dataHang.getString(0);
            arrayList.add(new Hang(MaHang,TenHang,SL));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogXoa(String tenSp,String maHang){
        Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa);
        dialog.show();

        TextView textXacNhanSPXoa= dialog.findViewById(R.id.XacNhanXoaTXT);

        Button btnXoa=(Button)dialog.findViewById(R.id.buttonXacNhanXoa);
        Button btnHuyXoa=(Button)dialog.findViewById(R.id.buttonHuy);


        textXacNhanSPXoa.setText("Bạn có cắc chắn muốn xóa "+tenSp+"?");


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    database.QuerryData("Delete from Hang WHERE MAHANG='"+maHang+"'");
                    Toast.makeText(HangTrongKho_Activity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    hienthiDL();
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
