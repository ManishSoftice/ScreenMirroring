package com.example.screenmirroring;

import java.util.ArrayList;

public class FolderModel {

    String str_folder;
    ArrayList<String> al_videoPath;

    public FolderModel(String string, ArrayList<String> al_path) {
        this.str_folder = string;
        this.al_videoPath = al_path;
    }

    public String getStr_folder() {
        return str_folder;
    }

    public void setStr_folder(String str_folder) {
        this.str_folder = str_folder;
    }

    public ArrayList<String> getAl_videoPath() {
        return al_videoPath;
    }

    public void setAl_videoPath(ArrayList<String> al_videoPath) {
        this.al_videoPath = al_videoPath;
    }
}
