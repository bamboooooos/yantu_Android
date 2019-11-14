package com.example.lin9080.yantu_lin;

public class Information {
    private String InforId;
    private String Title;
    private String Time;
    private String Detail;

    public Information(String inforId, String title, String time, String detail) {
        InforId =inforId;
        Title = title;
        Time = time;
        Detail = detail;
    }

    public String getInforId() {
        return InforId;
    }

    public void setInforId(String inforId) {
        InforId = inforId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }



}
