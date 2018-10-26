package ru.chertenok.spring.hibernate.util;

import org.springframework.ui.Model;
import ru.chertenok.spring.hibernate.config.Config;
import ru.chertenok.spring.hibernate.entity.Permission;

import java.util.List;

import static ru.chertenok.spring.hibernate.config.Config.*;

public class PageInfo {
    final private String URL;
    final private String TITLE;
    final private String SHABLON;
    final private boolean NEED_REPLACE;
    final private String PERMISSION;

    public PageInfo(String URL, String TITLE, String SHABLON) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = false;
        this.PERMISSION = "";
    }

    public PageInfo(String URL, String TITLE, String SHABLON,String PERMISSION) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = false;
        this.PERMISSION = PERMISSION;
    }

    public static boolean makeAllPlease(Model model, Config.PagesName pagesName) {

        String permission = PAGE_MAP.get(pagesName).getPERMISSION();
        boolean result = false;
        List<String> userPermissionList = Config.addUserToModel(model);

        if (permission.equals(""))
        {
            result = true;
        } else
        {
            result = userPermissionList.contains(permission);
        }

        if (result){
            model.addAttribute(BREADCRUMB, BREADCRUMB_MAP.get(pagesName));
        } else
        {
            model.addAttribute(BREADCRUMB, BREADCRUMB_MAP.get(PagesName.userAccessDenied));
        }

        return result;
    }

    public boolean isNEED_REPLACE() {
        return NEED_REPLACE;
    }

    public String getPERMISSION() {
        return (!PERMISSION.isEmpty())?Config.PREFIX_PERMISSION+PERMISSION:PERMISSION;
    }



    public PageInfo(String URL, String TITLE) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = "";
        this.NEED_REPLACE = false;
        this.PERMISSION = "";
    }

    public PageInfo(String URL, String TITLE,String[] PERMISSION) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = "";
        this.NEED_REPLACE = false;
        this.PERMISSION = "";
    }


    public PageInfo(String URL, String TITLE, String SHABLON, boolean NEED_REPLACE) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = NEED_REPLACE;
        this.PERMISSION = "";
    }

    public PageInfo(String URL, String TITLE, String SHABLON, boolean NEED_REPLACE,String PERMISSION) {
        this.URL = URL;
        this.TITLE = TITLE;
        this.SHABLON = SHABLON;
        this.NEED_REPLACE = NEED_REPLACE;
        this.PERMISSION = PERMISSION;
    }

    public String getSHABLON() {
        return SHABLON;
    }


    public String getURL() {
        return URL;
    }

    public String getURL_ReplaceID(String id) {
        return NEED_REPLACE ? URL.replaceAll("\\{id\\}", id) : getURL();
    }

    public String getURL_ReplaceID2(String id1, String id2) {
        return NEED_REPLACE ? URL.replaceAll("\\{id\\}", id1).replaceAll("\\{id2\\}", id2) : getURL();
    }

    public String getTITLE() {
        return TITLE;
    }

    public static  class   MakeAllResult{
        public boolean isAuthenticated;
        public String shablon;

        public MakeAllResult(boolean isAuthenticated, String shablon) {
            this.isAuthenticated = isAuthenticated;
            this.shablon = shablon;
        }

        public MakeAllResult() {
        }
    }
}
