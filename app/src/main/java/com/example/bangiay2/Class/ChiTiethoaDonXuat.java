package com.example.bangiay2.Class;

public class ChiTiethoaDonXuat {
    int MaHoaDon,soLuong;
    String MaHang,TenHang,NgayXuat;
    float giaXuat;

    public ChiTiethoaDonXuat(int maHoaDon, int soLuong, String maHang, String tenHang, String ngayXuat, float giaXuat) {
        MaHoaDon = maHoaDon;
        this.soLuong = soLuong;
        MaHang = maHang;
        TenHang = tenHang;
        NgayXuat = ngayXuat;
        this.giaXuat = giaXuat;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String maHang) {
        MaHang = maHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
    }

    public float getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(float giaXuat) {
        this.giaXuat = giaXuat;
    }
}
