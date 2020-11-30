package com.fortuneloan.tenmileslotus.bean;

import java.util.List;

public class WalletBillBean {

    /**
     * code : 200
     * msg :
     * order : [{"id":13996,"uid":139,"channel_id":null,"order_number":"order_FdW8kATTVuLBnL","order_loan_type":"1","order_loan_amount":"0000","order_loan_cycle":"62","amount":"298","pay_channel":"razorpay","pay_channel_order":null,"pay_stats":2,"pay_token":"LTWJ_SUNNYLOAN16002191507206","pay_RZToken":"pay_FdW8qFphyaVr5D","pay_msg":"SUCCESS","create_time":"2020-09-16 09:19:10","update_time":"2020-09-16 09:19:19","bank_card_number":"858859885889998"},{"id":13997,"uid":139,"channel_id":null,"order_number":"order_FdW9LElNDB8gxy","order_loan_type":"1","order_loan_amount":"0000","order_loan_cycle":"32","amount":"198","pay_channel":"razorpay","pay_channel_order":null,"pay_stats":2,"pay_token":"LTWJ_SUNNYLOAN16002191843325","pay_RZToken":"pay_FdW9RXA7bsFt8r","pay_msg":"SUCCESS","create_time":"2020-09-16 09:19:44","update_time":"2020-09-16 09:19:53","bank_card_number":"858859885889998"}]
     */

    private String code;
    private String msg;
    private List<OrderBean> order;

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

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * id : 13996
         * uid : 139
         * channel_id : null
         * order_number : order_FdW8kATTVuLBnL
         * order_loan_type : 1
         * order_loan_amount : 0000
         * order_loan_cycle : 62
         * amount : 298
         * pay_channel : razorpay
         * pay_channel_order : null
         * pay_stats : 2
         * pay_token : LTWJ_SUNNYLOAN16002191507206
         * pay_RZToken : pay_FdW8qFphyaVr5D
         * pay_msg : SUCCESS
         * create_time : 2020-09-16 09:19:10
         * update_time : 2020-09-16 09:19:19
         * bank_card_number : 858859885889998
         */

        private int id;
        private int uid;
        private Object channel_id;
        private String order_number;
        private String order_loan_type;
        private String order_loan_amount;
        private String order_loan_cycle;
        private String amount;
        private String pay_channel;
        private Object pay_channel_order;
        private int pay_stats;
        private String pay_token;
        private String pay_RZToken;
        private String pay_msg;
        private String create_time;
        private String update_time;
        private String bank_card_number;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(Object channel_id) {
            this.channel_id = channel_id;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getOrder_loan_type() {
            return order_loan_type;
        }

        public void setOrder_loan_type(String order_loan_type) {
            this.order_loan_type = order_loan_type;
        }

        public String getOrder_loan_amount() {
            return order_loan_amount;
        }

        public void setOrder_loan_amount(String order_loan_amount) {
            this.order_loan_amount = order_loan_amount;
        }

        public String getOrder_loan_cycle() {
            return order_loan_cycle;
        }

        public void setOrder_loan_cycle(String order_loan_cycle) {
            this.order_loan_cycle = order_loan_cycle;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPay_channel() {
            return pay_channel;
        }

        public void setPay_channel(String pay_channel) {
            this.pay_channel = pay_channel;
        }

        public Object getPay_channel_order() {
            return pay_channel_order;
        }

        public void setPay_channel_order(Object pay_channel_order) {
            this.pay_channel_order = pay_channel_order;
        }

        public int getPay_stats() {
            return pay_stats;
        }

        public void setPay_stats(int pay_stats) {
            this.pay_stats = pay_stats;
        }

        public String getPay_token() {
            return pay_token;
        }

        public void setPay_token(String pay_token) {
            this.pay_token = pay_token;
        }

        public String getPay_RZToken() {
            return pay_RZToken;
        }

        public void setPay_RZToken(String pay_RZToken) {
            this.pay_RZToken = pay_RZToken;
        }

        public String getPay_msg() {
            return pay_msg;
        }

        public void setPay_msg(String pay_msg) {
            this.pay_msg = pay_msg;
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

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }
    }
}
