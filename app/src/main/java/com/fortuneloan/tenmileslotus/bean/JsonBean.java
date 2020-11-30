package com.fortuneloan.tenmileslotus.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonBean {
    private String code;
    private String msg;
    private JSONObject orderData;
    private String payEnv;
    private String payJump;
    private String review;
    private String interest;
    private JSONArray dataArr;
    // payChaneel  1 razorpay 2 cashfree
    private String payChaneel;
    private String cycle;
    private String[] notice;
    private JSONObject text;

    public JSONObject getText() {
        return text;
    }

    public void setText(JSONObject text) {
        this.text = text;
    }


    public String[] getNotice() {
        return notice;
    }

    public void setNotice(String[] notice) {
        this.notice = notice;
    }


    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public JSONArray getDataArr() {
        return dataArr;
    }

    public void setDataArr(JSONArray dataArr) {
        this.dataArr = dataArr;
    }

    private List<PaymentChannel> paymentChannel;

    public String getPayChaneel() {
        return payChaneel;
    }

    public void setPayChaneel(String payChaneel) {
        this.payChaneel = payChaneel;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPayJump() {
        return payJump;
    }

    public void setPayJump(String payJump) {
        this.payJump = payJump;
    }

    public String getPayEnv() {
        return payEnv;
    }

    public void setPayEnv(String payEnv) {
        this.payEnv = payEnv;
    }

    public List<PaymentChannel> getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(List<PaymentChannel> paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public JSONObject getOrderData() {
        return orderData;
    }

    public void setOrderData(JSONObject orderData) {
        this.orderData = orderData;
    }

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
}
