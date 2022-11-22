package com.example.greenmanagement;

public class Tree {
    String tenKhoaHoc, tenThuongGoi, dacTinh, mauLa, linkHinhAnh, key;

    public Tree() {
    }

    public Tree(String tenKhoaHoc, String tenThuongGoi, String dacTinh, String mauLa, String linkHinhAnh, String key) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.tenThuongGoi = tenThuongGoi;
        this.dacTinh = dacTinh;
        this.mauLa = mauLa;
        this.linkHinhAnh = linkHinhAnh;
        this.key = key;
    }

    public Tree(String tenKhoaHoc, String tenThuongGoi, String dacTinh, String mauLa, String linkHinhAnh) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.tenThuongGoi = tenThuongGoi;
        this.dacTinh = dacTinh;
        this.mauLa = mauLa;
        this.linkHinhAnh = linkHinhAnh;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getTenThuongGoi() {
        return tenThuongGoi;
    }

    public void setTenThuongGoi(String tenThuongGoi) {
        this.tenThuongGoi = tenThuongGoi;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauLa() {
        return mauLa;
    }

    public void setMauLa(String mauLa) {
        this.mauLa = mauLa;
    }

    public String getLinkHinhAnh() {
        return linkHinhAnh;
    }

    public void setLinkHinhAnh(String linkHinhAnh) {
        this.linkHinhAnh = linkHinhAnh;
    }
}
