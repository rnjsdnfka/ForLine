package com.example.forline;

public class Memo {

    private String title;
    private String content;
    private String current_time;

    public Memo(String title, String content, String current_time) {
        this.title = title;
        this.content = content;
        this.current_time = current_time;
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

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }
}
