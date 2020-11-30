package com.fortuneloan.tenmileslotus.view.activitys

import android.os.Bundle
import android.widget.ImageView
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_about_us
    }

    override fun initView(savedInstanceState: Bundle?) {
        SPUtils.getInstance().put("isWeb", true)
    }

    override fun initListener() {
        iv_back!!.setOnClickListener { finish() }
    }

    override fun initData() {}
}