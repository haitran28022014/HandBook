package com.haitran.handbook.model;

import java.io.Serializable;

/**
 * Created by Hai Tran on 3/13/2017.
 */

public class ThucMonModel implements Serializable {
    private int id;
    private String monAnId;
    private String tenMonAn;
    private String thamKhao;
    private String hinhAnh;
    private String loai;
    private String soLuoc;
    private String nguyenLieu;
    private String cheBien;
    private String nenDung;
    private String thucPham;
    private String luuY;
    private String luongChat;

    public ThucMonModel(int id, String monAnId, String tenMonAn, String thamKhao, String hinhAnh, String loai, String soLuoc, String nguyenLieu, String cheBien, String nenDung, String thucPham, String luuY, String luongChat,String nhomChat) {
        this.id = id;
        this.monAnId = monAnId;
        this.tenMonAn = tenMonAn;
        this.thamKhao = thamKhao;
        this.hinhAnh = hinhAnh;
        this.loai = loai;
        this.soLuoc = soLuoc;
        this.nguyenLieu = nguyenLieu;
        this.cheBien = cheBien;
        this.nenDung = nenDung;
        this.thucPham = thucPham;
        this.luuY = luuY;
        this.luongChat = luongChat;
    }

    public ThucMonModel() {
    }

    public String getLuongChat() {
        return luongChat;
    }

    public String getLuuY() {
        return luuY;
    }

    public int getId() {
        return id;
    }

    public String getMonAnId() {
        return monAnId;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public String getThamKhao() {
        return thamKhao;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getLoai() {
        return loai;
    }

    public String getSoLuoc() {
        return soLuoc;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public String getCheBien() {
        return cheBien;
    }

    public String getNenDung() {
        return nenDung;
    }

    public String getThucPham() {
        return thucPham;
    }
}
