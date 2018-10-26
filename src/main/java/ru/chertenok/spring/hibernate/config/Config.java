package ru.chertenok.spring.hibernate.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.util.PageInfo;

import java.util.*;

public class Config {

    // список информации по каждой странице
    final public static Map<PagesName, PageInfo> PAGE_MAP = new HashMap();
    // навигация от корня до текущей страницы включительно,
    // последний элемент используется для получения информации о текущей странице
    // и проверки прав доступа
    final public static Map<PagesName, PageInfo[]> BREADCRUMB_MAP = new HashMap();
    // тоже самое при ошибке
    final public static Map<PagesName, PageInfo[]> BREADCRUMB_ERROR_MAP = new HashMap();
    // список прав
    final public static Map<Config.PermissionName, Permission> PERMISSION_MAP = new HashMap();
    final public static String BREADCRUMB = "breadcrumb";
    final public static String MESSAGE404 = "message";
    final public static String PREFIX_PERMISSION = "ROLE_";
    final public static String SQL_GET_USER_LIST = "SELECT NAME, PASSWORD, ENABLED FROM USERS WHERE NAME=?";
    final public static String SQL_GET_USER_WITH_PERMISSION = "SELECT u.name,p.name FROM users u" +
            " left JOIN  user_role ur ON u.id=ur.user_id " +
            " left JOIN role_permission rp ON rp.role_id=ur.role_id " +
            " left JOIN permissions p ON p.id=rp.permission_id " +
            " WHERE u.name = ?";

    // возвращает список прав текущего юзера и добавляет его в модель
    public static List<String> addUserToModel(Model model) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> listRole = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            listRole.add(authority.getAuthority());
        }
        model.addAttribute("userRole", listRole);
        return listRole;
    }

    public enum PagesName {
        home, page404, studentList, studentDetail, studentCourseEdit, courseList, courseDetail,
        studentCourseEditAdd, studentCourseEditRemove, mainShablon, userList, userLogin, userLoginForm, dataGenerate, userAccessDenied
    }

    public enum PermissionName {
        studentList, studentDetail, studentCourseEdit, courseList, courseDetail, userViewList, mainDataGenerate
    }

}
