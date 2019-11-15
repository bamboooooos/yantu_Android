package com.example.lin9080.yantu_lin;

import java.util.ArrayList;
import java.util.List;

public class MyTiezi {
    private int zan;//赞数
    private int pl;//评论数
    private String title;//贴子名
    private String content;//贴子内容
    private int id;//贴子id
    private String user_id;//用户id
    private List<Reply> replysList = new ArrayList<>();

    public List<Reply> getReplysList() {
        return replysList;
    }

    public void setReplysList(List<Reply> replysList) {
        this.replysList = replysList;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getPl() {
        return pl;
    }

    public void setPl(int pl) {
        this.pl = pl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}