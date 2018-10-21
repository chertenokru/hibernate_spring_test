package ru.chertenok.spring.hibernate.util;

import java.util.HashMap;
import java.util.Map;

public class Config {

    final public static Map<PagesName, PageInfo> PAGE_MAP = new HashMap();
    final public static String BREADCRUMB = "breadcrumb";
    final public static String MESSAGE404 = "message";

    public enum PagesName {
        home, page404, studentlist, studentDetail, studentCourseEdit, courseList, courseDetail,
        studentCourseEditAdd, studentCourseEditRemove
    }
}
