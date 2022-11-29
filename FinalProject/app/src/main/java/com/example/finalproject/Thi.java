package com.example.finalproject;

import java.util.ArrayList;

public class Thi {
    private String name, detail;
    private ArrayList<String> danhSachViDu;

    public Thi(String name, ArrayList<String> danhSachViDu) {
        this.name = name;
        this.danhSachViDu = danhSachViDu;
    }

    public Thi(String name, String detail) {
        this.detail = detail;
        this.name = name;
        this.danhSachViDu = new ArrayList<>();
    }

    public Thi() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDanhSachViDu() {
        return danhSachViDu;
    }

    public String danhSachViDuToString() {
        String str = "";
        for (int i = 0; i < this.danhSachViDu.size(); i++) {
            str += "•  " + this.danhSachViDu.get(i) + "\n";
        }
        return str;
    }

    public void setDanhSachViDu(ArrayList<String> danhSachViDu) {
        this.danhSachViDu = danhSachViDu;
    }

    public void themViDu(String viDu) {
        this.danhSachViDu.add(viDu);
    }
}
