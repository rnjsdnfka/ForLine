package com.example.forline;

public class Memo {

    private String title;
    private String content;
    private String current_time;
    private String detime;

    public Memo(String title, String content, String current_time,String detime) {
        this.title = title;
        this.content = content;
        this.current_time = current_time;
        this.detime = detime;
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

    public String getDetime() {
        return detime;
    }

    public void setDetime(String detime) {
        this.detime = detime;
    }
}
