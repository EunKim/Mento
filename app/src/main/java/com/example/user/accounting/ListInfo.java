package com.example.user.accounting;

/**
 * Created by user on 2016-08-04.
 */
public class ListInfo {

    private int icon;
    private String info;

    public ListInfo(int icon, String info){
        this.icon = icon;
        this.info = info;
    }

    public int getIcon() {
        return icon;
    }

    public String getInfo() {
        return info;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
