package com.example.bangiay2.Class;

public class Hang {
    String maHang,tenHang;
    int SoLuong;


    public Hang(String maHang, String tenHang, int soLuong) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        SoLuong = soLuong;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
