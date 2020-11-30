package com.fortuneloan.tenmileslotus.model

interface Api {
    companion object {
        const val BASE_URL = "http://fl.fortuneloan.in" //great wallet
        //首页接口
        const val HOME_DATA = "$BASE_URL/forlt/index/data_index"

        //文字提示接口
        const val TEXT_TIPS = "$BASE_URL/api/verifystep/getNoticeText"

        //获取登录验证码接口
        const val SEND = "$BASE_URL/forlt/login/login_code"

        //登录的接口
        const val LOGIN = "$BASE_URL/forlt/login/login"

        //后台控制认证的步骤
        const val VERIFYSTEP = "$BASE_URL/api/PocketCash/Authentication"

        //获取认证的状态
        const val VERIFYSTEPINFO = "$BASE_URL/api/FortuneLoan/Authentication"

        //编辑个人基本信息
        const val SUBMIT_INFO = "$BASE_URL/api/index/basic_info "

        //向服务器请求支付的参数
        const val GET_PAY_DATA = "$BASE_URL/forlt/pay/createRazorPayment"

        //支付结果的回调
        const val PAY_RESULT = "$BASE_URL/forlt/pay/payResultCallBack"

        //贷超
        const val LOAN_URL = "$BASE_URL/forlt/product/lt_product"

        //请求综合数据的接口
        const val ALL_BASE_DATA = "$BASE_URL/docs369/PocketCash/Pocket_data"

        //新的请求支付接口的数据
        const val NEW_PAY_DATA = "$BASE_URL/forlt/payment/payment"

        //传递uv和pv
        const val SEND_UV = "$BASE_URL/forlt/product/uvandpv"

        //协议的链接
        const val AGREEMENT = "$BASE_URL/docs258/Home/agreement"

        //提交问题反馈
        const val SUBMIT_QUESTION = "$BASE_URL/forlt/index/add_questions"

        //帮助中心问答列表
        const val QUESTION_ANSWER = "$BASE_URL/forlt/index/help"

        //提交人脸页面的数据
        const val SUBMIT_FACE = "$BASE_URL/lend921/face/face921"

        //提交ID卡的数据
        const val SUBMIT_ID = "$BASE_URL/smnny/LoseFace/IdAuth"

        //提交基本信息的页面
        const val SUBMIT_BASIC = "$BASE_URL/forlt/essential/raise"

        //提交银行卡信息的页面
        const val SUBMIT_BANK = "$BASE_URL/forlt/bank/add_bank"

        //提交审核页面的数据
        const val SUBMIT_AUTH = "$BASE_URL/forlt/auth/auth"

        //请求文字滚动的接口
        const val TEXT_ROLL = "$BASE_URL/forlt/index/roll_data"

        //钱包页面跳转到支付页面
        const val SUBMIT_WALLET = "$BASE_URL/forlt/wallet/md_wallet"

        //登录的链接
        const val LOGIN_AGREE_MENT = "$BASE_URL/privacy.html"

        //支付协议的链接
        const val PAY_AGREEMENT = "$BASE_URL/fortune_loan.html"

        //钱包账单
        const val WALLET_BILL = "$BASE_URL/forlt/wallet/bill"

        //修改银行卡的埋点
        const val UPDATE_BANK = "$BASE_URL/rwlim/wallet/upBank"

        //钱包页面的数据
        const val WALLET_DATA = "$BASE_URL/forlt/index/wallet"

        //增加金额页面的数据
        const val ADD_MONEY = "$BASE_URL/lend921/index/addMoney"

        //提交就业信息
        const val SUBMIT_EMPLOY = "$BASE_URL/forlt/employment/add_emp"

    }
}