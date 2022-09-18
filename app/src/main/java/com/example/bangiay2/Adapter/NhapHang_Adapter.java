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
import com.example.bangiay2.NhapHangActivity;
import com.example.bangiay2.R;
import com.example.bangiay2.Them_SP_Activity;

import java.util.List;

public class NhapHang_Adapter extends BaseAdapter {

    private NhapHangActivity context;
    private int layout;
    private List<Hang> HangList;
    Intent intent;

    public NhapHang_Adapter(NhapHangActivity context, int layout, List<Hang> hangList) {
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


    private class ViewHolderNhap{
        TextView tvTenSPNhap,tvMagiayNhap,tvSLNhap;
        ImageView imgNhap;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderNhap viewHolder;
        if(view==null){
            viewHolder= new ViewHolderNhap();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            //Anh xa
            viewHolder.tvTenSPNhap= view.findViewById(R.id.tensptrongkhonsptrongkhoNhap);
            viewHolder.tvMagiayNhap=view.findViewById(R.id.masptrongkhoNhap);
            viewHolder.tvSLNhap=view.findViewById(R.id.soluongsptrongkhoNhap);
            viewHolder.imgNhap=view.findViewById(R.id.btnNhapThemSP);
            view.setTag(viewHolder);


        }
        else{
            viewHolder= (ViewHolderNhap) view.getTag();
        }
        Hang Sp= HangList.get(i);
        String SL=Integer.toString(Sp.getSoLuong());
        viewHolder.tvTenSPNhap.setText(Sp.getTenHang());
        viewHolder.tvMagiayNhap.setText("Mã SP: "+Sp.getMaHang());
        viewHolder.tvSLNhap.setText("SL: "+SL);


        viewHolder.imgNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(context, Them_SP_Activity.class);
                String tenSpThem= Sp.getTenHang();
                String maSpThem= Sp.getMaHang();
                int SLSp= Sp.getSoLuong();
                String SlSpThem=Integer.toString(SLSp);
                intent.putExtra("maSpThem",maSpThem);
                intent.putExtra("tenSpThem",tenSpThem);
                intent.putExtra("SLSPThem",SlSpThem);
                context.startActivity(intent);
                Toast.makeText(context,"themsp",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
