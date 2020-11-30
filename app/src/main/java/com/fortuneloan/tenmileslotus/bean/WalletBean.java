package com.fortuneloan.tenmileslotus.bean;

public class WalletBean {

    /**
     * code : 200
     * msg :
     * option : {"quota_value":"\u20b9100,000","quota_interest_rate":"0.0002","may_quota":"20,000","FaceAuth_add":"3000","BasicInfo_add":"6000","IDAuth_add":"4000"}
     * transfered : ₹0.00
     * totalInterest : ₹0.00
     * bank_card : **** **** **** 8855
     */

    private String code;
    private String msg;
    private OptionBean option;
    private String transfered;
    private String totalInterest;
    private String bank_card;

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

    public OptionBean getOption() {
        return option;
    }

    public void setOption(OptionBean option) {
        this.option = option;
    }

    public String getTransfered() {
        return transfered;
    }

    public void setTransfered(String transfered) {
        this.transfered = transfered;
    }

    public String getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(String totalInterest) {
        this.totalInterest = totalInterest;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public static class OptionBean {
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
