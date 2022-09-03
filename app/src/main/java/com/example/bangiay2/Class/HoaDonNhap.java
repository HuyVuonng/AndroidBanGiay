package com.example.bangiay2.Class;

public class HoaDonNhap {
    String ngayTaoHoaDon;
    int MaHoaDon;

    public HoaDonNhap(String ngayTaoHoaDon, int maHoaDon) {
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
}
