package com.jenuvid.scrnmirroring.model;

public class ThemeModel {
    public int themelist;
    int themelistapply;
    int device;

    public ThemeModel(int themelist, int themelistapply, int device) {
        this.themelist = themelist;
        this.themelistapply = themelistapply;
        this.device = device;
    }

    public int getThemelist() {
        return themelist;
    }

    public void setThemelist(int themelist) {
        this.themelist = themelist;
    }

    public int getThemelistapply() {
        return themelistapply;
    }

    public void setThemelistapply(int themelistapply) {
        this.themelistapply = themelistapply;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }
}
