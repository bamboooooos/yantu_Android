package com.example.lin9080.yantu_lin;

public class Plan {
    private String userid;
    private String plans;
    private String calendar;
    private int id;

    public String getUser() {
        return userid;
    }

    public String getPlan() {
        return plans;
    }

    public String getCalendar() { return calendar; }

    public int getId() { return id; }

    public void setId(int id) { this.id=id; }

    public void setUser(String user) {
        this.userid = user;
    }

    public void setPlan(String plan) {
        this.plans = plan;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}