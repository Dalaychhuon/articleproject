package com.example.dalay.projecthrd.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {
    @Expose
    @SerializedName("TOTAL_PAGES")
    private int totalPages;
    @Expose
    @SerializedName("TOTAL_COUNT")
    private int totalCount;
    @Expose
    @SerializedName("LIMIT")
    private int limit;
    @Expose
    @SerializedName("PAGE")
    private int page;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
