package com.lockscreenamongus.utils;

public class Wallpaper {
    private int url;
    private int index;

    public Wallpaper(int url, int index) {
        this.url = url;
        this.index = index;
    }
    public Wallpaper(){

    }
    public void setUrl(int url) {
        this.url = url;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getUrl() {
        return url;
    }

    public int getIndex() {
        return index;
    }
}