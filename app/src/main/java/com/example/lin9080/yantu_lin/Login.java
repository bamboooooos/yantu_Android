package com.example.lin9080.yantu_lin;

public class Login {
    String phone;
    String password;
    String namel;
    int major;
    int year;

    public Login(String phone, String password, String namel, int major, int year) {
        this.phone = phone;
        this.password = password;
        this.namel = namel;
        this.major = major;
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
