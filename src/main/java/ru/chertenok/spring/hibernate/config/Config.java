package ru.chertenok.spring.hibernate.config;

import ru.chertenok.spring.hibernate.util.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class Config {

    final public static Map<PagesName, PageInfo> PAGE_MAP = new HashMap();
    final public static String BREADCRUMB = "breadcrumb";
    final public static String MESSAGE404 = "message";

    public enum PagesName {
        home, page404, studentList, studentDetail, studentCourseEdit, courseList, courseDetail,
        studentCourseEditAdd, studentCourseEditRemove,mainShablon,userList,userLogin,userAccessDenied
    }

}
