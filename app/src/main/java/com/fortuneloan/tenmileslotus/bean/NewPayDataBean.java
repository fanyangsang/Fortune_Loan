package com.fortuneloan.tenmileslotus.bean;

import java.util.List;

public class NewPayDataBean {

    /**
     * code : 200
     * msg : success！
     * loan_info : [{"id":13,"loan_time":"92days","gross_interset":"0","amount":"299","create_time":"2020-08-07 09:37:30","update_time":"2020-08-17 20:52:35","interest":"","loan_amount":"\u20b910000"},{"id":10,"loan_time":"92days","gross_interset":"0","amount":"542","create_time":"2020-06-05 15:03:41","update_time":"2020-08-17 20:52:47","interest":"0","loan_amount":"\u20b920000"},{"id":11,"loan_time":"92days","gross_interset":"0","amount":"798","create_time":"2020-06-05 15:03:57","update_time":"2020-08-17 20:53:06","interest":"0","loan_amount":"\u20b930000"}]
     */

    private String code;
    private String msg;
    private List<LoanInfoBean> loan_info;
    private String writing;
    private String copy;

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

    public List<LoanInfoBean> getLoan_info() {
        return loan_info;
    }

    public void setLoan_info(List<LoanInfoBean> loan_info) {
        this.loan_info = loan_info;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public static class LoanInfoBean {
        /**
         * id : 13
         * loan_time : 92days
         * gross_interset : 0
         * amount : 299
         * create_time : 2020-08-07 09:37:30
         * update_time : 2020-08-17 20:52:35
         * interest :
         * loan_amount : ₹10000
         */

        private int id;
        private String loan_time;
        private String gross_interset;
        private String amount;
        private String create_time;
        private String update_time;
        private String interest;
        private String loan_amount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoan_time() {
            return loan_time;
        }

        public void setLoan_time(String loan_time) {
            this.loan_time = loan_time;
        }

        public String getGross_interset() {
            return gross_interset;
        }

        public void setGross_interset(String gross_interset) {
            this.gross_interset = gross_interset;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getLoan_amount() {
            return loan_amount;
        }

        public void setLoan_amount(String loan_amount) {
            this.loan_amount = loan_amount;
        }
    }
}
