package ru.chertenok.spring.hibernate.util;

public class PageInfo {
    final private String URL;
    final private String TITLE;
    final private String SHABLON;

    public PageInfo(String URL, String TITLE, String SHABLON) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
    }

    public String getSHABLON() {
        return SHABLON;
    }

    public String getURL() {
        return URL;
    }

    public String getTITLE() {
        return TITLE;
    }
}
