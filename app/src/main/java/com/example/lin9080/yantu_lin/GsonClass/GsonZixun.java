package com.example.lin9080.yantu_lin.GsonClass;

public class GsonZixun {
    private int id;
    private String zn;
    private String zs;
    private String zb;

    public GsonZixun(int id, String zn, String zs, String zb) {
        this.id = id;
        this.zn = zn;
        this.zs = zs;
        this.zb = zb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb;
    }
}
