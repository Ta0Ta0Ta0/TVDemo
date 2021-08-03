package com.example.myapplication.media;

import java.util.List;

public class Music {

    private int code;
    private String msg;
    private List<ResDTO> res;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResDTO> getRes() {
        return res;
    }

    public void setRes(List<ResDTO> res) {
        this.res = res;
    }
}
