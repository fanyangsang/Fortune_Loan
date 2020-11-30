package com.fortuneloan.tenmileslotus.view.activitys

import android.os.Bundle
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import kotlinx.android.synthetic.main.activity_credit.*

class CreditActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_credit
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_score.text = SPUtils.getInstance().getString("creditScore")
        tv_personal.text = SPUtils.getInstance().getString("personalScore")
        tv_employment.text = SPUtils.getInstance().getString("employScore")
        tv_bank_card.text = SPUtils.getInstance().getString("bankScore")




    }

    override fun initListener() {

        iv_back.setOnClickListener { finish() }


    }

    override fun initData() {

    }

}