package com.haitran.handbook.model;

import java.io.Serializable;

/**
 * Created by Hai Tran on 3/10/2017.
 */

public class ThaiKyModel implements Serializable {
    private int id;
    private String thaiNhi;
    private String chat;
    private String monAn;
    private String thucPham;
    private String ten;
    private String thaiKyId;
    private String thamKhao;
    private String tamCaNguyet;
    private String baBau;
    private String hinhAnh;

    public ThaiKyModel(int id, String thaiNhi, String chat, String monAn, String thucPham, String ten, String thaiKyId, String thamKhao, String tamCaNguyet, String baBau, String hinhAnh) {
        this.id = id;
        this.thaiNhi = thaiNhi;
        this.chat = chat;
        this.monAn = monAn;
        this.thucPham = thucPham;
        this.ten = ten;
        this.thaiKyId = thaiKyId;
        this.thamKhao = thamKhao;
        this.tamCaNguyet = tamCaNguyet;
        this.baBau = baBau;
        this.hinhAnh = hinhAnh;
    }

    public ThaiKyModel() {
    }

    public int getId() {
        return id;
    }

    public String getThaiNhi() {
        return thaiNhi;
    }

    public String getChat() {
        return chat;
    }

    public String getMonAn() {
        return monAn;
    }

    public String getThucPham() {
        return thucPham;
    }

    public String getTen() {
        return ten;
    }

    public String getThaiKyId() {
        return thaiKyId;
    }

    public String getThamKhao() {
        return thamKhao;
    }

    public String getTamCaNguyet() {
        return tamCaNguyet;
    }

    public String getBaBau() {
        return baBau;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }
}
