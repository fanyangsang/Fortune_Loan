package com.fortuneloan.tenmileslotus.view.activitys

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.Window
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.JsonBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import com.fortuneloan.tenmileslotus.utils.RandomUtils
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.layout_title.*

class AuthenticationActivity : BaseActivity() {
    private var dialog: ProgressDialog? = null
    var handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val str = msg.obj as String
            when (msg.what) {
                100 -> {
                    ll_gif_auth.visibility = View.GONE
                    tv_submit.visibility = View.VISIBLE
                    rl_after_auth.visibility = View.VISIBLE

                    val personalScore = RandomUtils.random(87, 98)
                    val employScore = RandomUtils.random(80, 95)
                    val bankScore = RandomUtils.random(86, 96)
                    var creditScore = (personalScore + employScore + bankScore) / 3
                    if (creditScore % 3 === 2) {
                        creditScore = creditScore + 1
                    }
                    tv_may_quota.text = "₹" + str
                    tv_score.text = ""+creditScore
                    //将几个分数存入缓存
                    SPUtils.getInstance().put("personalScore",""+personalScore)
                    SPUtils.getInstance().put("employScore",""+employScore)
                    SPUtils.getInstance().put("bankScore",""+bankScore)
                    SPUtils.getInstance().put("creditScore",""+creditScore)
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_auth
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_titles!!.text = "Review"
        //从缓存中获取最大可借额度，然后进行倒计时
        val mayQuota: String? = SPUtils.getInstance().getString("may_quota")
        val message = Message()
        message!!.what = 100
        message.obj = mayQuota
        handler.sendMessageDelayed(message, 9000)
    }

    override fun initListener() {
        tv_submit.setOnClickListener { submitData() }
        iv_back.setOnClickListener { finish() }
    }

    override fun initData() {}
    override fun onResume() {
        super.onResume()
        //初始化dialog
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog!!.setMessage("Loading...")
        }
    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    /**
     * 提交数据
     */
    private fun submitData() {
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_AUTH)
                .headers(headers)
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        if (dialog != null && !dialog!!.isShowing) {
                            dialog!!.show()
                        }
                    }

                    override fun onResponse(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        val gson = Gson()
                        val allDataBean = gson.fromJson(json, JsonBean::class.java)
                        if ("200" == allDataBean.code) {
                            SPUtils.getInstance().put("is_tip", 4)
                            val intent = Intent(this@AuthenticationActivity, WalletActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onError(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                    }
                })
    }
}