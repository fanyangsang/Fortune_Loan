package com.fortuneloan.tenmileslotus.bean;

public class AddMoneyBean {

    /**
     * code : 200
     * msg : success
     * data : {"may_quota":"\u20b920,000","FaceAuth_add":"+\u20b93000","BasicInfo_add":"+\u20b96000","Bank_add":"+\u20b94000","shenghe":"\u20b933000","shang":"+3000","zhong":"+6000","xia":"+4000"}
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
         * may_quota : ₹20,000
         * FaceAuth_add : +₹3000
         * BasicInfo_add : +₹6000
         * Bank_add : +₹4000
         * shenghe : ₹33000
         * shang : +3000
         * zhong : +6000
         * xia : +4000
         */

        private String may_quota;
        private String FaceAuth_add;
        private String BasicInfo_add;
        private String Bank_add;
        private String shenghe;
        private String shang;
        private String zhong;
        private String xia;

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

        public String getBank_add() {
            return Bank_add;
        }

        public void setBank_add(String Bank_add) {
            this.Bank_add = Bank_add;
        }

        public String getShenghe() {
            return shenghe;
        }

        public void setShenghe(String shenghe) {
            this.shenghe = shenghe;
        }

        public String getShang() {
            return shang;
        }

        public void setShang(String shang) {
            this.shang = shang;
        }

        public String getZhong() {
            return zhong;
        }

        public void setZhong(String zhong) {
            this.zhong = zhong;
        }

        public String getXia() {
            return xia;
        }

        public void setXia(String xia) {
            this.xia = xia;
        }
    }
}
