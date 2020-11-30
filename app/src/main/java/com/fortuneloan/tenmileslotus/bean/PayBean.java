package com.fortuneloan.tenmileslotus.bean;

public class PayBean {

    /**
     * code : 200
     * msg : success
     * orderData : {"appId":"rzp_test_P5Hi4thA7rxT6k","image":"https://bkimg.cdn.bcebos.com/pic/2f738bd4b31c870168f8cf9f257f9e2f0708ff79","name":"SUPER LOAN","description":"order_FJW36TFUkmn2ro","orderNumber":"order_FJW36TFUkmn2ro","cftoken":"SUPERLOAN20200727081348CFPG138697311","orderAmount":25900,"orderCurrency":"INR"}
     */

    private String code;
    private String msg;
    private OrderDataBean orderData;

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

    public OrderDataBean getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderDataBean orderData) {
        this.orderData = orderData;
    }

    public static class OrderDataBean {
        /**
         * appId : rzp_test_P5Hi4thA7rxT6k
         * image : https://bkimg.cdn.bcebos.com/pic/2f738bd4b31c870168f8cf9f257f9e2f0708ff79
         * name : SUPER LOAN
         * description : order_FJW36TFUkmn2ro
         * orderNumber : order_FJW36TFUkmn2ro
         * cftoken : SUPERLOAN20200727081348CFPG138697311
         * orderAmount : 25900
         * orderCurrency : INR
         */

        private String appId;
        private String image;
        private String name;
        private String description;
        private String orderNumber;
        private String cftoken;
        private int orderAmount;
        private String orderCurrency;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getCftoken() {
            return cftoken;
        }

        public void setCftoken(String cftoken) {
            this.cftoken = cftoken;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderCurrency() {
            return orderCurrency;
        }

        public void setOrderCurrency(String orderCurrency) {
            this.orderCurrency = orderCurrency;
        }
    }
}
