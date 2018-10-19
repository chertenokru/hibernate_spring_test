package ru.chertenok.spring.hibernate.util;

public class PageInfo {
    final private String URL;
    final private String TITLE;
    final private String SHABLON;
    final private boolean NEED_REPLACE;

    public PageInfo(String URL, String TITLE, String SHABLON) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = false;
    }

    public PageInfo(String URL, String TITLE, String SHABLON, boolean NEED_REPLACE) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = NEED_REPLACE;
    }

    public String getSHABLON() {
        return SHABLON;
    }


    public String getURL() {
        return URL;
    }

    public String getURL_ReplaceID(String id) {
        return NEED_REPLACE?URL.replaceAll("\\{id\\}",id):getURL();
    }

    public String getURL_ReplaceID2(String id1,String id2) {
        return NEED_REPLACE?URL.replaceAll("\\{id\\}",id1).replaceAll("\\{id2\\}",id2):getURL();
    }
    public String getTITLE() {
        return TITLE;
    }
}
