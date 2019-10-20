package com.dev.lvc.baitap.models;

public class Menu {
    private int icon;
    private String title;

    public Menu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public Menu() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
