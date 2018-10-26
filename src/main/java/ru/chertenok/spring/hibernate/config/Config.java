package ru.chertenok.spring.hibernate.config;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.util.PageInfo;

import java.util.*;

public class Config {

    final public static Map<PagesName, PageInfo> PAGE_MAP = new HashMap();
    final public static Map<Config.PermissionName, Permission> PERMISSION_MAP = new HashMap();
    final public static String BREADCRUMB = "breadcrumb";
    final public static String MESSAGE404 = "message";

    public enum PagesName {
        home, page404, studentList, studentDetail, studentCourseEdit, courseList, courseDetail,
        studentCourseEditAdd, studentCourseEditRemove,mainShablon,userList,userLogin,userAccessDenied
    }
    public enum PermissionName {
        studentList,studentDetail,studentCourseEdit,courseList,courseDetail,userViewList
    }

    public static List<String> addUserToModel(Model model){
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> listRole = new ArrayList<>();

        for (GrantedAuthority authority : authorities) {
            listRole.add(authority.getAuthority());
        }
        model.addAttribute("userRole",listRole);
        return listRole;
    }

}
