package dev.tatuan.hh.TrangChu;

public class Data {
    public String id, tenDH,giaDH, loaiDH, matKinh, nangLuong, xuatXu, linkHA;

    public Data() {
    }

    public Data(String id, String tenDH, String giaDH, String loaiDH, String matKinh, String nangLuong, String xuatXu, String linkHA) {
        this.id = id;
        this.tenDH = tenDH;
        this.giaDH = giaDH;
        this.loaiDH = loaiDH;
        this.matKinh = matKinh;
        this.nangLuong = nangLuong;
        this.xuatXu = xuatXu;
        this.linkHA = linkHA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDH() {
        return tenDH;
    }

    public void setTenDH(String tenDH) {
        this.tenDH = tenDH;
    }

    public String getGiaDH() {
        return giaDH;
    }

    public void setGiaDH(String giaDH) {
        this.giaDH = giaDH;
    }

    public String getLoaiDH() {
        return loaiDH;
    }

    public void setLoaiDH(String loaiDH) {
        this.loaiDH = loaiDH;
    }

    public String getMatKinh() {
        return matKinh;
    }

    public void setMatKinh(String matKinh) {
        this.matKinh = matKinh;
    }

    public String getNangLuong() {
        return nangLuong;
    }

    public void setNangLuong(String nangLuong) {
        this.nangLuong = nangLuong;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getLinkHA() {
        return linkHA;
    }

    public void setLinkHA(String linkHA) {
        this.linkHA = linkHA;
    }
}
