package ru.chertenok.spring.hibernate.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationListFactory {
    private final List<PageInfo> pageInfoList = new ArrayList<>();
    private PageInfo pageInfo;
    private long pageCount;

    public PaginationListFactory(PageInfo pageInfo, long pageCount) {
        this.pageInfo = pageInfo;
        this.pageCount = pageCount;
        createPage();
    }

    private void createPage() {
        pageInfoList.clear();
        System.out.println("создаю новый список страниц");

        for (int i = 1; i <= pageCount; i++) {
            pageInfoList.add(new PageInfo(pageInfo.getURL() + "?page=" + i, "" + i));
        }
    }


    public List<PageInfo> getList(long pageCount, PageInfo pageInfo) {
        if (pageCount != this.pageCount || pageInfo != this.pageInfo) {
            this.pageCount = pageCount;
            this.pageInfo = pageInfo;
            createPage();
        }
        return pageInfoList;
    }

}
