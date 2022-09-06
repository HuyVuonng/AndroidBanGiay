package com.example.bangiay2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangiay2.Class.Hang;
import com.example.bangiay2.HangTrongKho_Activity;
import com.example.bangiay2.R;
import com.example.bangiay2.SuaSP_Activity;

import java.util.List;

public class Hang_Adapter extends BaseAdapter
{
    //để gọi được hàm trong HangTrongKho_Activity thì Context phải là HangTrongKho_Activity
    private HangTrongKho_Activity context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public Hang_Adapter(HangTrongKho_Activity context, int layout, List<Hang> hangList) {
        this.context = context;
        this.layout = layout;
        HangList = hangList;
    }


    @Override
    public int getCount() {
        return HangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTenSP,tvMagiay,tvSL,tvgia;
        ImageView imgSua,imgXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tvTenSP= view.findViewById(R.id.tensptrongkhonsptrongkho);
            viewHolder.tvMagiay=view.findViewById(R.id.masptrongkho);
            viewHolder.tvSL=view.findViewById((R.id.soluongsptrongkho));
            viewHolder.imgSua=view.findViewById(R.id.btnedit);
            viewHolder.imgXoa=view.findViewById(R.id.btndelete);
            viewHolder.tvgia=view.findViewById(R.id.giasptrongkho);


        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Hang Sp= HangList.get(i);
        String SL=Integer.toString(Sp.getSoLuong());
        String giaSp= Float.toString(Sp.getGia());
        viewHolder.tvTenSP.setText(Sp.getTenHang());
        viewHolder.tvMagiay.setText("Mã SP: "+Sp.getMaHang());
        viewHolder.tvSL.setText("SL: "+SL);
        viewHolder.tvgia.setText("Giá: "+giaSp);


        viewHolder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(context, SuaSP_Activity.class);

                String tenSpSua= Sp.getTenHang();
                String maSpSua= Sp.getMaHang();
                int SLSp= Sp.getSoLuong();
                String SlSpSua=Integer.toString(SLSp);
                intent.putExtra("maSpSua",maSpSua);
                intent.putExtra("tenSpSua",tenSpSua);
                intent.putExtra("SLSPSua",SlSpSua);
                context.startActivity(intent);

            }
        });

        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(Sp.getTenHang(),Sp.getMaHang());
            }
        });
        return view;
    }
}
