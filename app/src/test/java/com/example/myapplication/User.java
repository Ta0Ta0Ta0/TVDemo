package com.example.myapplication;

import java.util.List;

public class User {


    private boolean isDeveloper;
    private String name;
    private int age;
    private String email;
    private List<ProgramSeriesDTO> programSeries;

    public boolean isIsDeveloper() {
        return isDeveloper;
    }

    public void setIsDeveloper(boolean isDeveloper) {
        this.isDeveloper = isDeveloper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProgramSeriesDTO> getProgramSeries() {
        return programSeries;
    }

    public void setProgramSeries(List<ProgramSeriesDTO> programSeries) {
        this.programSeries = programSeries;
    }

    public static class ProgramSeriesDTO {
        private String contentType;
        private String psId;

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
    }
}
