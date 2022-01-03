package com.example.screenmirroring;

public class VideoModel {

    String str_path,str_thumb,title;
    boolean boolean_selected;
    long duration;

    public VideoModel(String str_path, String str_thumb, boolean boolean_selected,long duration ,String title) {
        this.str_path = str_path;
        this.str_thumb = str_thumb;
        this.boolean_selected = boolean_selected;
        this.duration = duration;
        this.title = title;
    }

    public String getStr_path() {
        return str_path;
    }

    public void setStr_path(String str_path) {
        this.str_path = str_path;
    }

    public String getStr_thumb() {
        return str_thumb;
    }

    public void setStr_thumb(String str_thumb) {
        this.str_thumb = str_thumb;
    }

    public boolean isBoolean_selected() {
        return boolean_selected;
    }

    public void setBoolean_selected(boolean boolean_selected) {
        this.boolean_selected = boolean_selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}

