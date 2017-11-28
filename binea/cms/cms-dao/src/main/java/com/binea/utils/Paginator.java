package com.binea.utils;

import java.util.Arrays;

/**
 * Created by binea
 * Date: 28/11/2017
 * TIME: 10:43 PM
 */
public class Paginator {

    private long total = 0;
    private int page = 1;
    private long totalPage = 1;
    private int rows = 10;
    private int step = 5;
    private String param = "page";
    private String url = "";
    private String query = "";

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public void setRows(int rows) {
        if (rows > 1000) {
            rows = 1000;
        }
        this.rows = rows;
        initTotalPage();
    }

    private void initTotalPage() {
        totalPage = (total % rows) == 0 ? (total / rows) : ((total / rows) + 1);
        if (page > totalPage) {
            page = (int) totalPage;
        }

        if (page < 1) {
            page = 1;
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return 0;
    }

    public int getRows() {
        return 0;
    }

    public String getHtml() {
        if (query != null) {
            final String[] params = {""};
            String[] querys = query.split("&");
            Arrays.stream(querys).forEach(s -> {
                if (s.startsWith(param)) {
                    return;
                }

                if (s.equals("")) {
                    params[0] = s;
                } else {
                    params[0] += "&" + s;
                }
            });
            if (!params.equals("")) {
                url += "?" + params;
            }
        }

        // 结果html
        String pages = "";

        int pageCount = (int) Math.ceil((double) total / rows);// 求总页数
        if (pageCount <= 1) {
            return pages;
        }
        if (page > pageCount) {
            page = pageCount;// 如果分页变量大总页数，则将分页变量设计为总页数
        }
        if (page <= 0) {
            page = 1;// 如果分页变量小于１,则将分页变量设为１
        }

        // 显示上一页
        if (page > 1) {
            if (url.contains("?")) {
                pages = pages.concat(
                        "<a class=\"prev\" href=\"" + url + "&" + param + "=" + (page - 1) + "\">上一页</a>\n");
            } else {
                pages = pages.concat(
                        "<a class=\"prev\" href=\"" + url + "?" + param + "=" + (page - 1) + "\">上一页</a>\n");
            }
        } else {
            // 特定需求可隐藏
            pages = pages.concat("<a class=\"prev\" href=\"javascript:;\" style=\"color:#ccc\">上一页</a>\n");
        }
        // 如果总页数大于要显示的个数，则拼接显示
        if (pageCount > step) {
            // 显示分页码
            int listBegin = (page - (int) Math.floor((double) step / 2));// 从第几页开始显示分页信息
            if (listBegin < 1) {
                listBegin = 1;
            }
            // 显示第1页
            if (listBegin >= 2) {
                if (url.contains("?")) {
                    pages = pages.concat("<a href=\"" + url + "&" + param + "=1\">1</a> ... \n");
                } else {
                    pages = pages.concat("<a href=\"" + url + "?" + param + "=1\">1</a> ... \n");
                }
            }
            // 当前页数右侧还有未显示页码时
            if (pageCount - page >= page - listBegin) {
                for (int i = listBegin; i < (listBegin + step); i++) {
                    pages = computePages(pages, i);
                }
                // 显示最后1页
                if (listBegin + step <= pageCount) {
                    if (url.contains("?")) {
                        pages = pages.concat(
                                " ... <a href=\"" + url + "&" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
                    } else {
                        pages = pages.concat(
                                " ... <a href=\"" + url + "?" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
                    }
                }
            } else { // 显示最后剩余的几个页码
                for (int i = (pageCount - step) + 1; i <= pageCount; i++) {
                    pages = computePages(pages, i);
                }
            }
        } else { // 总页数小于等于step时，直接显示
            for (int i = 1; i <= pageCount; i++) {
                pages = computePages(pages, i);
            }
        }
        // 显示下一页
        if (page < pageCount) {
            if (url.contains("?")) {
                pages = pages.concat(
                        "<a class=\"next\" href=\"" + url + "&" + param + "=" + (page + 1) + "\">下一页</a>\n");
            } else {
                pages = pages.concat(
                        "<a class=\"next\" href=\"" + url + "?" + param + "=" + (page + 1) + "\">下一页</a>\n");
            }
        } else {
            // 特定需求可隐藏
            pages = pages.concat("<a class=\"next\" href=\"javascript:;\" style=\"color:#ccc\">下一页</a>\n");
        }
        return pages;
    }

    private String computePages(String pages, int i) {
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
