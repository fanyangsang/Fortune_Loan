package com.fortuneloan.tenmileslotus.bean;

import com.google.gson.annotations.SerializedName;

public class NewHomeBean {

    /**
     * code : 200
     * msg : success
     * loan : {"quota_value":"\u20b9100,000","quota_interest_rate":"0.0002","may_quota":"20,000","FaceAuth_add":"3000","BasicInfo_add":"6000","IDAuth_add":"4000"}
     * switch : 2
     * email : pocketcashservice@outlook.com
     * range : 20,000~₹100,000
     */

    private String code;
    private String msg;
    private LoanBean loan;
    @SerializedName("switch")
    private int switchX;
    private String email;
    private String range;

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

    public LoanBean getLoan() {
        return loan;
    }

    public void setLoan(LoanBean loan) {
        this.loan = loan;
    }

    public int getSwitchX() {
        return switchX;
    }

    public void setSwitchX(int switchX) {
        this.switchX = switchX;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public static class LoanBean {
        /**
         * quota_value : ₹100,000
         * quota_interest_rate : 0.0002
         * may_quota : 20,000
         * FaceAuth_add : 3000
         * BasicInfo_add : 6000
         * IDAuth_add : 4000
         */

        private String quota_value;
        private String quota_interest_rate;
        private String may_quota;
        private String FaceAuth_add;
        private String BasicInfo_add;
        private String IDAuth_add;

        public String getQuota_value() {
            return quota_value;
        }

        public void setQuota_value(String quota_value) {
            this.quota_value = quota_value;
        }

        public String getQuota_interest_rate() {
            return quota_interest_rate;
        }

        public void setQuota_interest_rate(String quota_interest_rate) {
            this.quota_interest_rate = quota_interest_rate;
        }

        public String getMay_quota() {
            return may_quota;
        }

        public void setMay_quota(String may_quota) {
            this.may_quota = may_quota;
        }

        public String getFaceAuth_add() {
            return FaceAuth_add;
        }

        public void setFaceAuth_add(String FaceAuth_add) {
            this.FaceAuth_add = FaceAuth_add;
        }

        public String getBasicInfo_add() {
            return BasicInfo_add;
        }

        public void setBasicInfo_add(String BasicInfo_add) {
            this.BasicInfo_add = BasicInfo_add;
        }

        public String getIDAuth_add() {
            return IDAuth_add;
        }

        public void setIDAuth_add(String IDAuth_add) {
            this.IDAuth_add = IDAuth_add;
        }
    }
}
