package com.example.myapplication.practice;

import java.util.List;

public class ItemInfo {


    private String count;
    private String pageNo;
    private String pageSize;
    private String totalPage;
    private List<ProgramSeriesDTO> programSeries;
    private int isShowCount;
    private int isShowRecommendation;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<ProgramSeriesDTO> getProgramSeries() {
        return programSeries;
    }

    public void setProgramSeries(List<ProgramSeriesDTO> programSeries) {
        this.programSeries = programSeries;
    }

    public int getIsShowCount() {
        return isShowCount;
    }

    public void setIsShowCount(int isShowCount) {
        this.isShowCount = isShowCount;
    }

    public int getIsShowRecommendation() {
        return isShowRecommendation;
    }

    public void setIsShowRecommendation(int isShowRecommendation) {
        this.isShowRecommendation = isShowRecommendation;
    }
}



