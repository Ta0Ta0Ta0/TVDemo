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

    public static class ProgramSeriesDTO {
        private String contentType;
        private String psId;
        private String name;
        private String hImg;
        private String vImg;
        private String mImg;
        private String squareImg;
        private List<CornerDTO> corner;
        private String updateDate;
        private String channelName;
        private String channelLogo;
        private String playCounts;
        private String isEnd;
        private String grade;
        private Object totalCount;
        private Object nowCount;
        private Object programTypeId;
        private String intent;
        private String recommendation;
        private Object freeStartTime;
        private Object freeEndTime;
        private int chargeType;
        private String cpCode;
        private String cpName;
        private String maskDescription;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getPsId() {
            return psId;
        }

        public void setPsId(String psId) {
            this.psId = psId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHImg() {
            return hImg;
        }

        public void setHImg(String hImg) {
            this.hImg = hImg;
        }

        public String getVImg() {
            return vImg;
        }

        public void setVImg(String vImg) {
            this.vImg = vImg;
        }

        public String getMImg() {
            return mImg;
        }

        public void setMImg(String mImg) {
            this.mImg = mImg;
        }

        public String getSquareImg() {
            return squareImg;
        }

        public void setSquareImg(String squareImg) {
            this.squareImg = squareImg;
        }

        public List<CornerDTO> getCorner() {
            return corner;
        }

        public void setCorner(List<CornerDTO> corner) {
            this.corner = corner;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelLogo() {
            return channelLogo;
        }

        public void setChannelLogo(String channelLogo) {
            this.channelLogo = channelLogo;
        }

        public String getPlayCounts() {
            return playCounts;
        }

        public void setPlayCounts(String playCounts) {
            this.playCounts = playCounts;
        }

        public String getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(String isEnd) {
            this.isEnd = isEnd;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public Object getNowCount() {
            return nowCount;
        }

        public void setNowCount(Object nowCount) {
            this.nowCount = nowCount;
        }

        public Object getProgramTypeId() {
            return programTypeId;
        }

        public void setProgramTypeId(Object programTypeId) {
            this.programTypeId = programTypeId;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }

        public Object getFreeStartTime() {
            return freeStartTime;
        }

        public void setFreeStartTime(Object freeStartTime) {
            this.freeStartTime = freeStartTime;
        }

        public Object getFreeEndTime() {
            return freeEndTime;
        }

        public void setFreeEndTime(Object freeEndTime) {
            this.freeEndTime = freeEndTime;
        }

        public int getChargeType() {
            return chargeType;
        }

        public void setChargeType(int chargeType) {
            this.chargeType = chargeType;
        }

        public String getCpCode() {
            return cpCode;
        }

        public void setCpCode(String cpCode) {
            this.cpCode = cpCode;
        }

        public String getCpName() {
            return cpName;
        }

        public void setCpName(String cpName) {
            this.cpName = cpName;
        }

        public String getMaskDescription() {
            return maskDescription;
        }

        public void setMaskDescription(String maskDescription) {
            this.maskDescription = maskDescription;
        }

        public static class CornerDTO {
            private String cornerImg;
            private int position;

            public String getCornerImg() {
                return cornerImg;
            }

            public void setCornerImg(String cornerImg) {
                this.cornerImg = cornerImg;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }
        }
    }
}



