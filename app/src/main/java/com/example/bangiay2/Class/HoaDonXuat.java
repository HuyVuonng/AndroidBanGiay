package com.example.bangiay2.Class;

public class HoaDonXuat {
    String ngayTaoHoaDon;
    int MaHoaDon;
    float tongtien;

    public HoaDonXuat(String ngayTaoHoaDon, int maHoaDon, float tongtien) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        MaHoaDon = maHoaDon;
        this.tongtien = tongtien;
    }

    public HoaDonXuat(String ngayTaoHoaDon, int maHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        MaHoaDon = maHoaDon;
    }

    public String getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
}
