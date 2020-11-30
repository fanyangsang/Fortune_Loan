package com.fortuneloan.tenmileslotus.bean;

public class LoginBean {


    /**
     * code : 200
     * msg : 请求成功!
     * data : {"uid":13869,"token":"a836e2caa8fadfa56a807dbe7622f4e1","user_info":{"id":13869,"uid":null,"name":"fanfan","id_number":null,"mobile":"15713843885","customer_status":0,"occupation":null,"area":null,"basic_network_time":null,"basic_total_employment":"2016","basic_security_fund":null,"basic_monthly_income":"20,000~3,0000","detail":null,"province":null,"id_sex":"Male","id_age":"21","id_address":null,"work_address":null,"channel_id":null,"last_login_ip":"60.186.10.80","last_login_time":"2020-07-29 14:21:05","device_name":"unknown","device_type":0,"model":"V1911A","device_brand_name":null,"bank_name":"HDFC Bank","bank_card_number":"123456879999","bank_mobile":null,"bank_update_time":null,"contact_status":0,"pay_user_extend":null,"extend":null,"delete_status":1,"is_vip":1,"create_time":"2020-07-25 10:35:21","update_time":"2020-07-28 20:44:01","register_ip":"115.198.216.159","deduction":0,"oil_register":0,"school":"University","marriage":"Single","email":"fanyangcloud@gmail.com","loanAmount":null,"photo_face":null,"photo_id_front":null,"photo_id_back":null,"md_faceauth":null,"md_idauth":null,"md_basicinfo":1,"md_mount_verify":null,"md_payment_select":1,"md_finish":1,"md_wallet":null,"md_bindcard":1,"is_tip":null,"work_type":"Full Time","md_employment":1,"is_new":0}}
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
         * uid : 13869
         * token : a836e2caa8fadfa56a807dbe7622f4e1
         * user_info : {"id":13869,"uid":null,"name":"fanfan","id_number":null,"mobile":"15713843885","customer_status":0,"occupation":null,"area":null,"basic_network_time":null,"basic_total_employment":"2016","basic_security_fund":null,"basic_monthly_income":"20,000~3,0000","detail":null,"province":null,"id_sex":"Male","id_age":"21","id_address":null,"work_address":null,"channel_id":null,"last_login_ip":"60.186.10.80","last_login_time":"2020-07-29 14:21:05","device_name":"unknown","device_type":0,"model":"V1911A","device_brand_name":null,"bank_name":"HDFC Bank","bank_card_number":"123456879999","bank_mobile":null,"bank_update_time":null,"contact_status":0,"pay_user_extend":null,"extend":null,"delete_status":1,"is_vip":1,"create_time":"2020-07-25 10:35:21","update_time":"2020-07-28 20:44:01","register_ip":"115.198.216.159","deduction":0,"oil_register":0,"school":"University","marriage":"Single","email":"fanyangcloud@gmail.com","loanAmount":null,"photo_face":null,"photo_id_front":null,"photo_id_back":null,"md_faceauth":null,"md_idauth":null,"md_basicinfo":1,"md_mount_verify":null,"md_payment_select":1,"md_finish":1,"md_wallet":null,"md_bindcard":1,"is_tip":null,"work_type":"Full Time","md_employment":1,"is_new":0}
         */

        private int uid;
        private String token;
        private UserInfoBean user_info;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * id : 13869
             * uid : null
             * name : fanfan
             * id_number : null
             * mobile : 15713843885
             * customer_status : 0
             * occupation : null
             * area : null
             * basic_network_time : null
             * basic_total_employment : 2016
             * basic_security_fund : null
             * basic_monthly_income : 20,000~3,0000
             * detail : null
             * province : null
             * id_sex : Male
             * id_age : 21
             * id_address : null
             * work_address : null
             * channel_id : null
             * last_login_ip : 60.186.10.80
             * last_login_time : 2020-07-29 14:21:05
             * device_name : unknown
             * device_type : 0
             * model : V1911A
             * device_brand_name : null
             * bank_name : HDFC Bank
             * bank_card_number : 123456879999
             * bank_mobile : null
             * bank_update_time : null
             * contact_status : 0
             * pay_user_extend : null
             * extend : null
             * delete_status : 1
             * is_vip : 1
             * create_time : 2020-07-25 10:35:21
             * update_time : 2020-07-28 20:44:01
             * register_ip : 115.198.216.159
             * deduction : 0
             * oil_register : 0
             * school : University
             * marriage : Single
             * email : fanyangcloud@gmail.com
             * loanAmount : null
             * photo_face : null
             * photo_id_front : null
             * photo_id_back : null
             * md_faceauth : null
             * md_idauth : null
             * md_basicinfo : 1
             * md_mount_verify : null
             * md_payment_select : 1
             * md_finish : 1
             * md_wallet : null
             * md_bindcard : 1
             * is_tip : null
             * work_type : Full Time
             * md_employment : 1
             * is_new : 0
             */

            private int id;
            private Object uid;
            private String name;
            private Object id_number;
            private String mobile;
            private int customer_status;
            private Object occupation;
            private Object area;
            private Object basic_network_time;
            private String basic_total_employment;
            private Object basic_security_fund;
            private String basic_monthly_income;
            private Object detail;
            private Object province;
            private String id_sex;
            private String id_age;
            private Object id_address;
            private Object work_address;
            private Object channel_id;
            private String last_login_ip;
            private String last_login_time;
            private String device_name;
            private int device_type;
            private String model;
            private Object device_brand_name;
            private String bank_name;
            private String bank_card_number;
            private Object bank_mobile;
            private Object bank_update_time;
            private int contact_status;
            private Object pay_user_extend;
            private Object extend;
            private int delete_status;
            private int is_vip;
            private String create_time;
            private String update_time;
            private String register_ip;
            private int deduction;
            private int oil_register;
            private String school;
            private String marriage;
            private String email;
            private Object loanAmount;
            private Object photo_face;
            private Object photo_id_front;
            private Object photo_id_back;
            private Object md_faceauth;
            private Object md_idauth;
            private int md_basicinfo;
            private Object md_mount_verify;
            private int md_payment_select;
            private int md_finish;
            private Object md_wallet;
            private int md_bindcard;
            private int is_tip;
            private String work_type;
            private int md_employment;
            private int is_new;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getUid() {
                return uid;
            }

            public void setUid(Object uid) {
                this.uid = uid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getId_number() {
                return id_number;
            }

            public void setId_number(Object id_number) {
                this.id_number = id_number;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getCustomer_status() {
                return customer_status;
            }

            public void setCustomer_status(int customer_status) {
                this.customer_status = customer_status;
            }

            public Object getOccupation() {
                return occupation;
            }

            public void setOccupation(Object occupation) {
                this.occupation = occupation;
            }

            public Object getArea() {
                return area;
            }

            public void setArea(Object area) {
                this.area = area;
            }

            public Object getBasic_network_time() {
                return basic_network_time;
            }

            public void setBasic_network_time(Object basic_network_time) {
                this.basic_network_time = basic_network_time;
            }

            public String getBasic_total_employment() {
                return basic_total_employment;
            }

            public void setBasic_total_employment(String basic_total_employment) {
                this.basic_total_employment = basic_total_employment;
            }

            public Object getBasic_security_fund() {
                return basic_security_fund;
            }

            public void setBasic_security_fund(Object basic_security_fund) {
                this.basic_security_fund = basic_security_fund;
            }

            public String getBasic_monthly_income() {
                return basic_monthly_income;
            }

            public void setBasic_monthly_income(String basic_monthly_income) {
                this.basic_monthly_income = basic_monthly_income;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public String getId_sex() {
                return id_sex;
            }

            public void setId_sex(String id_sex) {
                this.id_sex = id_sex;
            }

            public String getId_age() {
                return id_age;
            }

            public void setId_age(String id_age) {
                this.id_age = id_age;
            }

            public Object getId_address() {
                return id_address;
            }

            public void setId_address(Object id_address) {
                this.id_address = id_address;
            }

            public Object getWork_address() {
                return work_address;
            }

            public void setWork_address(Object work_address) {
                this.work_address = work_address;
            }

            public Object getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(Object channel_id) {
                this.channel_id = channel_id;
            }

            public String getLast_login_ip() {
                return last_login_ip;
            }

            public void setLast_login_ip(String last_login_ip) {
                this.last_login_ip = last_login_ip;
            }

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public int getDevice_type() {
                return device_type;
            }

            public void setDevice_type(int device_type) {
                this.device_type = device_type;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public Object getDevice_brand_name() {
                return device_brand_name;
            }

            public void setDevice_brand_name(Object device_brand_name) {
                this.device_brand_name = device_brand_name;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_card_number() {
                return bank_card_number;
            }

            public void setBank_card_number(String bank_card_number) {
                this.bank_card_number = bank_card_number;
            }

            public Object getBank_mobile() {
                return bank_mobile;
            }

            public void setBank_mobile(Object bank_mobile) {
                this.bank_mobile = bank_mobile;
            }

            public Object getBank_update_time() {
                return bank_update_time;
            }

            public void setBank_update_time(Object bank_update_time) {
                this.bank_update_time = bank_update_time;
            }

            public int getContact_status() {
                return contact_status;
            }

            public void setContact_status(int contact_status) {
                this.contact_status = contact_status;
            }

            public Object getPay_user_extend() {
                return pay_user_extend;
            }

            public void setPay_user_extend(Object pay_user_extend) {
                this.pay_user_extend = pay_user_extend;
            }

            public Object getExtend() {
                return extend;
            }

            public void setExtend(Object extend) {
                this.extend = extend;
            }

            public int getDelete_status() {
                return delete_status;
            }

            public void setDelete_status(int delete_status) {
                this.delete_status = delete_status;
            }

            public int getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(int is_vip) {
                this.is_vip = is_vip;
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

            public String getRegister_ip() {
                return register_ip;
            }

            public void setRegister_ip(String register_ip) {
                this.register_ip = register_ip;
            }

            public int getDeduction() {
                return deduction;
            }

            public void setDeduction(int deduction) {
                this.deduction = deduction;
            }

            public int getOil_register() {
                return oil_register;
            }

            public void setOil_register(int oil_register) {
                this.oil_register = oil_register;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getMarriage() {
                return marriage;
            }

            public void setMarriage(String marriage) {
                this.marriage = marriage;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getLoanAmount() {
                return loanAmount;
            }

            public void setLoanAmount(Object loanAmount) {
                this.loanAmount = loanAmount;
            }

            public Object getPhoto_face() {
                return photo_face;
            }

            public void setPhoto_face(Object photo_face) {
                this.photo_face = photo_face;
            }

            public Object getPhoto_id_front() {
                return photo_id_front;
            }

            public void setPhoto_id_front(Object photo_id_front) {
                this.photo_id_front = photo_id_front;
            }

            public Object getPhoto_id_back() {
                return photo_id_back;
            }

            public void setPhoto_id_back(Object photo_id_back) {
                this.photo_id_back = photo_id_back;
            }

            public Object getMd_faceauth() {
                return md_faceauth;
            }

            public void setMd_faceauth(Object md_faceauth) {
                this.md_faceauth = md_faceauth;
            }

            public Object getMd_idauth() {
                return md_idauth;
            }

            public void setMd_idauth(Object md_idauth) {
                this.md_idauth = md_idauth;
            }

            public int getMd_basicinfo() {
                return md_basicinfo;
            }

            public void setMd_basicinfo(int md_basicinfo) {
                this.md_basicinfo = md_basicinfo;
            }

            public Object getMd_mount_verify() {
                return md_mount_verify;
            }

            public void setMd_mount_verify(Object md_mount_verify) {
                this.md_mount_verify = md_mount_verify;
            }

            public int getMd_payment_select() {
                return md_payment_select;
            }

            public void setMd_payment_select(int md_payment_select) {
                this.md_payment_select = md_payment_select;
            }

            public int getMd_finish() {
                return md_finish;
            }

            public void setMd_finish(int md_finish) {
                this.md_finish = md_finish;
            }

            public Object getMd_wallet() {
                return md_wallet;
            }

            public void setMd_wallet(Object md_wallet) {
                this.md_wallet = md_wallet;
            }

            public int getMd_bindcard() {
                return md_bindcard;
            }

            public void setMd_bindcard(int md_bindcard) {
                this.md_bindcard = md_bindcard;
            }

            public int getIs_tip() {
                return is_tip;
            }

            public void setIs_tip(int is_tip) {
                this.is_tip = is_tip;
            }

            public String getWork_type() {
                return work_type;
            }

            public void setWork_type(String work_type) {
                this.work_type = work_type;
            }

            public int getMd_employment() {
                return md_employment;
            }

            public void setMd_employment(int md_employment) {
                this.md_employment = md_employment;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }
        }
    }
}
