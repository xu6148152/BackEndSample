package com.binea.common.util;

/**
 * Created by binea
 * Date: 9/12/2017
 * TIME: 3:11 PM
 */
public class PageUtil {
    public static String computePages(String pages, int i, int page, String url, String param) {
        if (i != page) {
            if (url.contains("?")) {
                pages = pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
            } else {
                pages = pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
            }
        } else {
            pages = pages.concat("<span class=\"current\">" + i + "</span>\n");
        }
        return pages;
    }
}
