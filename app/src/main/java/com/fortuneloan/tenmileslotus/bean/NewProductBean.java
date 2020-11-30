package com.fortuneloan.tenmileslotus.bean;

import java.util.List;

public class NewProductBean {

    /**
     * data : {"product":[{"goods_name":"PaySense","goods_brief":"Instant Personal Loans","goods_pic":"http://pl.plusloan.in/upload/admin/20200529/3c6638eeb17c606b6848b5f7429d4fdc.png","goods_url":"https://play.google.com/store/apps/details?id=com.indiaBulls","people_num":"9827","amount":"80000","goods_feature_term":300,"goods_feature_hot":1,"goods_feature_new":1,"goods_feature_fast":2,"goods_feature_recommend":1,"goods_feature_fenqi":1,"goods_feature_low":1,"product_id":105,"product_icon":["http://ls.loan-super.in/upload/portal/icon/hot.png","http://ls.loan-super.in/upload/portal/icon/new.png","http://ls.loan-super.in/upload/portal/icon/tuijian.png","http://ls.loan-super.in/upload/portal/icon/fenqi.png","http://ls.loan-super.in/upload/portal/icon/low.png"]},{"goods_name":"MoneyTap","goods_brief":"Instant Personal Loan","goods_pic":"http://pl.plusloan.in/upload/admin/20200529/b792093f66a9374bc260c6a0a9c37a89.png","goods_url":"https://app.appsflyer.com/com.loan.cash.credit.easy.dhan.quick.udhaar.lend.game.jaldi.paisa.borrow.rupee.play.kredit?af_siteid=10463&af_sub3=sub_id&pid=mobisummer_int&clickid=transaction_id&aff_sub3=offer_id&c=mobisummer_int","people_num":"9827","amount":"80000","goods_feature_term":300,"goods_feature_hot":1,"goods_feature_new":1,"goods_feature_fast":1,"goods_feature_recommend":1,"goods_feature_fenqi":1,"goods_feature_low":1,"product_id":106,"product_icon":["http://ls.loan-super.in/upload/portal/icon/hot.png","http://ls.loan-super.in/upload/portal/icon/new.png","http://ls.loan-super.in/upload/portal/icon/fast.png","http://ls.loan-super.in/upload/portal/icon/tuijian.png","http://ls.loan-super.in/upload/portal/icon/fenqi.png","http://ls.loan-super.in/upload/portal/icon/low.png"]}]}
     */

    private DataBean data;
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProductBean> product;

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * goods_name : PaySense
             * goods_brief : Instant Personal Loans
             * goods_pic : http://pl.plusloan.in/upload/admin/20200529/3c6638eeb17c606b6848b5f7429d4fdc.png
             * goods_url : https://play.google.com/store/apps/details?id=com.indiaBulls
             * people_num : 9827
             * amount : 80000
             * goods_feature_term : 300
             * goods_feature_hot : 1
             * goods_feature_new : 1
             * goods_feature_fast : 2
             * goods_feature_recommend : 1
             * goods_feature_fenqi : 1
             * goods_feature_low : 1
             * product_id : 105
             * product_icon : ["http://ls.loan-super.in/upload/portal/icon/hot.png","http://ls.loan-super.in/upload/portal/icon/new.png","http://ls.loan-super.in/upload/portal/icon/tuijian.png","http://ls.loan-super.in/upload/portal/icon/fenqi.png","http://ls.loan-super.in/upload/portal/icon/low.png"]
             */

            private String goods_name;
            private String goods_brief;
            private String goods_pic;
            private String goods_url;
            private String people_num;
            private String amount;
            private String peopleNum;
            private int goods_feature_term;
            private int goods_feature_hot;
            private int goods_feature_new;
            private int goods_feature_fast;
            private int goods_feature_recommend;
            private int goods_feature_fenqi;
            private int goods_feature_low;
            private int product_id;
            private List<String> product_icon;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_brief() {
                return goods_brief;
            }

            public void setGoods_brief(String goods_brief) {
                this.goods_brief = goods_brief;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public String getGoods_url() {
                return goods_url;
            }

            public void setGoods_url(String goods_url) {
                this.goods_url = goods_url;
            }

            public String getPeople_num() {
                return people_num;
            }

            public void setPeople_num(String people_num) {
                this.people_num = people_num;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getGoods_feature_term() {
                return goods_feature_term;
            }

            public void setGoods_feature_term(int goods_feature_term) {
                this.goods_feature_term = goods_feature_term;
            }

            public int getGoods_feature_hot() {
                return goods_feature_hot;
            }

            public void setGoods_feature_hot(int goods_feature_hot) {
                this.goods_feature_hot = goods_feature_hot;
            }

            public int getGoods_feature_new() {
                return goods_feature_new;
            }

            public void setGoods_feature_new(int goods_feature_new) {
                this.goods_feature_new = goods_feature_new;
            }

            public int getGoods_feature_fast() {
                return goods_feature_fast;
            }

            public void setGoods_feature_fast(int goods_feature_fast) {
                this.goods_feature_fast = goods_feature_fast;
            }

            public int getGoods_feature_recommend() {
                return goods_feature_recommend;
            }

            public void setGoods_feature_recommend(int goods_feature_recommend) {
                this.goods_feature_recommend = goods_feature_recommend;
            }

            public int getGoods_feature_fenqi() {
                return goods_feature_fenqi;
            }

            public void setGoods_feature_fenqi(int goods_feature_fenqi) {
                this.goods_feature_fenqi = goods_feature_fenqi;
            }

            public int getGoods_feature_low() {
                return goods_feature_low;
            }

            public void setGoods_feature_low(int goods_feature_low) {
                this.goods_feature_low = goods_feature_low;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public List<String> getProduct_icon() {
                return product_icon;
            }

            public void setProduct_icon(List<String> product_icon) {
                this.product_icon = product_icon;
            }

            public String getPeopleNum() {
                return peopleNum;
            }

            public void setPeopleNum(String peopleNum) {
                this.peopleNum = peopleNum;
            }
        }
    }
}
