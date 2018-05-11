package dev.tatuan.hh.GioHanng;

public class GioHangData {
    String id, soluong, tendh, tongtien, hinhanh;

    public GioHangData() {
    }

    public GioHangData(String id, String soluong, String tendh, String tongtien, String hinhanh) {

        this.id = id;
        this.soluong = soluong;
        this.tendh = tendh;
        this.tongtien = tongtien;
        this.hinhanh = hinhanh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTendh() {
        return tendh;
    }

    public void setTendh(String tendh) {
        this.tendh = tendh;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
