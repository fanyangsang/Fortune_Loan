package com.fortuneloan.tenmileslotus.bean;

public class TouristDataBean {

    /**
     * code : 403
     * msg : Login expire!
     * data : {"can_quota":{"quota_value":"\u20b9100,000","quota_interest_rate":"0.0002","may_quota":"20,000"}}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * can_quota : {"quota_value":"\u20b9100,000","quota_interest_rate":"0.0002","may_quota":"20,000"}
         */

        private CanQuotaBean can_quota;

        public CanQuotaBean getCan_quota() {
            return can_quota;
        }

        public void setCan_quota(CanQuotaBean can_quota) {
            this.can_quota = can_quota;
        }

        public static class CanQuotaBean {
            /**
             * quota_value : ₹100,000
             * quota_interest_rate : 0.0002
             * may_quota : 20,000
             */

            private String quota_value;
            private String quota_interest_rate;
            private String may_quota;

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
        }
    }
}
