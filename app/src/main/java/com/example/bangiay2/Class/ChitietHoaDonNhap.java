package com.example.bangiay2.Class;

public class ChitietHoaDonNhap {
    int MaHoaDon,soLuong;
    String MaHang,TenHang,NgayNhap;
    float giaNhap;

    public ChitietHoaDonNhap(int maHoaDon, int soLuong, String maHang, String tenHang, String ngayNhap) {
        MaHoaDon = maHoaDon;
        this.soLuong = soLuong;
        MaHang = maHang;
        TenHang = tenHang;
        NgayNhap = ngayNhap;
    }

    public ChitietHoaDonNhap(int maHoaDon, int soLuong, String maHang, String tenHang, String ngayNhap, float giaNhap) {
        MaHoaDon = maHoaDon;
        this.soLuong = soLuong;
        MaHang = maHang;
        TenHang = tenHang;
        NgayNhap = ngayNhap;
        this.giaNhap = giaNhap;
    }

    public ChitietHoaDonNhap(int maHoaDon, float giaNhap) {
        MaHoaDon = maHoaDon;
        this.giaNhap = giaNhap;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
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
}
