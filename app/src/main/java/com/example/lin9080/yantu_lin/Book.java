package com.example.lin9080.yantu_lin;

public class Book {
    private String bookId;
    private String bookname;
    private String bookMode="辅导书";
    private String url;

    public Book(String bookId, String bookname, String bookMode, String url) {
        this.bookId = bookId;
        this.bookname = bookname;
        this.bookMode = bookMode;
        this.url = url;
    }

    public Book(String bookId, String bookname, String url) {
        this.bookId = bookId;
        this.bookname = bookname;
        this.url = url;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookMode() {
        return bookMode;
    }

    public void setBookMode(String bookMode) {
        this.bookMode = bookMode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
