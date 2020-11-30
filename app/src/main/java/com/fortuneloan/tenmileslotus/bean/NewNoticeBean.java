package com.fortuneloan.tenmileslotus.bean;

import java.util.List;

public class NewNoticeBean {


    /**
     * code : 200
     * msg :
     * data : ["740****713 successfully received \u20b930000","883****534 successfully received \u20b950000","982****943 successfully received \u20b950000","840****563 successfully received \u20b930000","940****183 successfully received \u20b920000","742****073 successfully received \u20b930000","940****411 successfully received \u20b930000","720****183 successfully received \u20b950000","810****073 successfully received \u20b930000","820****411 successfully received \u20b920000"]
     */

    private String code;
    private String msg;
    private List<String> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
