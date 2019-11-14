package com.example.lin9080.yantu_lin;

public class User {
    private String password;
    private String tel;
    private String name;
    private int major;
    private int years;
    private int uplan;
    private String head;

    public User(String password, String tel, String name, int major, int years, int uplan, String head) {
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.major = major;
        this.years = years;
        this.uplan = uplan;
        this.head = head;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getUplan() {
        return uplan;
    }

    public void setUplan(int uplan) {
        this.uplan = uplan;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
